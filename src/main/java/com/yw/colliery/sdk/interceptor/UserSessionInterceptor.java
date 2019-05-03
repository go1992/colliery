package com.yw.colliery.sdk.interceptor;

import com.yw.colliery.sdk.helper.UserSessionHelper;
import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.service.user.CollierySafetyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: renzhiqiang
 * @Description: 用户session处理拦截器(太消耗数据库性能了，先暂时不开启)
 * @Date: 2019/5/3
 */
@Component
public class UserSessionInterceptor implements HandlerInterceptor{
    @Autowired
    private UserSessionHelper userSessionHelper;
    @Autowired
    private CollierySafetyUserService collierySafetyUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户信息是否被修改，如果被修改了需要更新session中用户信息
        UserRelationEntity userRealtion = LoginSessionUtils.getUser();
        if (userRealtion != null) {
            //根据更新时间来判断，不相等则说明用户被修改了
            CollierySafetyUserEntity safetyUser = collierySafetyUserService.selectyUserId(userRealtion.getSafetyUser().getId());
            if (userRealtion.getSafetyUser().getModifyDate() == safetyUser.getModifyDate()
                    || userRealtion.getSafetyUser().getModifyDate().equals(safetyUser.getModifyDate())){
                //用户信息没有修改
                return true;
            } else {
                //用户信息被修改
                userSessionHelper.updateUserRelationInfo();
            }
            return true;
        } else {
            //session中没有用户信息，不能继续调用接口
            return false;
        }
    }
}
