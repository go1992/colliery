package com.yw.colliery.api.login.filter;

import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.sdk.constans.LoginConstant;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.sdk.utils.SpringSessionUtils;
import com.yw.colliery.user.CollierySafetyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author renzhiqiang
 * @Description session过滤器
 * @Date 2019-04-29
 **/
@Component
public class SessionFilter implements Filter {
    @Autowired
    private CollierySafetyUserService collierySafetyUserService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //先取，如果没有则查询用户信息，查到就放到session中
        CollierySafetyUserEntity safetyUser = LoginSessionUtils.getUser();
        if (safetyUser == null) {
            safetyUser = collierySafetyUserService.selectByUserCode(servletRequest.getParameter(LoginConstant.Request.USER_CODE));
            if (safetyUser != null) {
                SpringSessionUtils.setSession(LoginConstant.Session.WEBAPI_SESSION_USER, safetyUser);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
