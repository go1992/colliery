package com.yw.colliery.login.service;

import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sinosig.scsf.utils.RedisClientUtil.redisClientUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author liulu-lhq
 * @sinece 2018/12/4
 */
@DependsOn(value = "redisClientUtil")
@Component
public class ValidationCodeFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (checkTypeAndReq(httpServletRequest)) {
            validate(httpServletRequest, httpServletResponse);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    private boolean checkTypeAndReq(HttpServletRequest httpServletRequest) {
        return StringUtils.equals(ScsfConstant.SCSF_LOGIN, httpServletRequest.getRequestURI())
                && StringUtils.equalsIgnoreCase(ScsfConstant.REQ_METHOD_POST, httpServletRequest.getMethod());
    }

    private void validate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws ServletException, IOException {
        String actualCode = redisClientUtil.hGet(ScsfConstant.SCSF_VALID_CODE, httpServletRequest.getSession().getId(), new TypeReference<String>() {
        });
        String codeInRequest = httpServletRequest.getParameter(ScsfConstant.SCSF_IMAGE_CODE);
        if (StringUtils.isBlank(codeInRequest)) {
            loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse
                    , new ValidCodeAuthenticationException("验证码为空或者不存在！"));
            return;
        }
        if (StringUtils.isBlank(actualCode)) {
            loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse
                    , new ValidCodeAuthenticationException("请重新获取最新验证码！"));
            return;
        }
        if (!StringUtils.equals(codeInRequest.trim().toUpperCase(), actualCode)) {
            loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse
                    , new ValidCodeAuthenticationException("验证码不匹配！"));
            return;
        }
        List<String> hDelList = new ArrayList<>();
        hDelList.add(httpServletRequest.getSession().getId());
        redisClientUtil.hDel(ScsfConstant.SCSF_VALID_CODE, hDelList);
        //redisTemplate.opsForHash().delete(ScsfConstant.SCSF_VALID_CODE, httpServletRequest.getSession().getId());
    }
}
