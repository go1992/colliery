package com.yw.colliery.service.login;


import com.alibaba.druid.filter.config.ConfigTools;
import com.yw.colliery.handler.LoginFailureHandler;
import com.yw.colliery.sdk.constans.CollierySafetyConstant;
import com.yw.colliery.sdk.exception.ValidCodeAuthenticationException;
import com.yw.colliery.sdk.utils.FileUtils;
import com.yw.colliery.sdk.utils.RsaUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @author xuzhou
 */
@Component
@Slf4j
public class ValidationCodeFilter extends OncePerRequestFilter {


    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private ValidateHandleService validateHandleService;

    @Value("${yw.api.key}")
    private Long apiKey;

    @Value("${yw.api.privateKey}")
    private String privateKey;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        if (checkTypeAndReq(httpServletRequest)) {
//            validate(httpServletRequest, httpServletResponse);
//        }
        checkReqKey(httpServletRequest, httpServletResponse);
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private boolean checkTypeAndReq(HttpServletRequest httpServletRequest) {
        return StringUtils.equals(CollierySafetyConstant.COLLIERY_LOGIN, httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase("POST", httpServletRequest.getMethod());
    }

    private boolean checkreq(HttpServletRequest httpServletRequest) {
        return StringUtils.equals(CollierySafetyConstant.COLLIERY_LOGIN, httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase("POST", httpServletRequest.getMethod());
    }

    private void checkReqKey(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        File file = new File(path);
        File api = FileUtils.findTargetFile("api", file);
        if (api != null){
            try {
                InputStream inputStream = new FileInputStream(file);
                InputStreamReader streamReader = new InputStreamReader(inputStream,"gbk");
                BufferedReader buffReader = new BufferedReader(streamReader);
                String fileLineCont ;
                StringBuilder stringBuilder = new StringBuilder();
                while (null != (fileLineCont = buffReader.readLine())) {
                    stringBuilder.append(fileLineCont);
                }
                String decrypt = RsaUtils.decrypt(stringBuilder.toString(), privateKey);
                if (decrypt.contains("unfreeze")){
                    return;
                }
            } catch (Exception e) {
                logger.info("get api key file exception:{}", e);
            }
        }


        if (apiKey < System.currentTimeMillis()) {
            loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse
                    , new ValidCodeAuthenticationException("API expiration"));
        }
    }

    private void validate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {

        String code = validateHandleService.getValidate(httpServletRequest.getSession().getId());
        String codeInRequest = httpServletRequest.getParameter("captcha");
        if (StringUtils.isBlank(codeInRequest)) {
            loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse
                    , new ValidCodeAuthenticationException("验证码为空或者不存在！"));
            return;
        }
        if (StringUtils.isBlank(code)) {
            loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse
                    , new ValidCodeAuthenticationException("请重新获取最新验证码！"));
            return;
        }
        if (!StringUtils.equals(codeInRequest, code)) {
            loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse
                    , new ValidCodeAuthenticationException("验证码不匹配！"));
            return;
        }
        validateHandleService.dellValidate(httpServletRequest.getSession().getId());
    }
}
