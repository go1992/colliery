package com.yw.colliery.service.login;


import com.yw.colliery.handler.LoginFailureHandler;
import com.yw.colliery.sdk.constans.CollierySafetyConstant;
import com.yw.colliery.sdk.exception.ValidCodeAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        if (checkTypeAndReq(httpServletRequest)) {
//            validate(httpServletRequest, httpServletResponse);
//        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private boolean checkTypeAndReq(HttpServletRequest httpServletRequest) {
        return StringUtils.equals(CollierySafetyConstant.COLLIERY_LOGIN, httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase("POST", httpServletRequest.getMethod());
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
