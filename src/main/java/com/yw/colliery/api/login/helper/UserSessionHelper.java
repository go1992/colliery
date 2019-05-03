package com.yw.colliery.api.login.helper;

import com.yw.colliery.entity.user.UserAuthEntity;
import com.yw.colliery.sdk.constans.LoginConstant;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.sdk.utils.SpringSessionUtils;
import com.yw.colliery.service.user.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: renzhiqiang
 * @Description: 用户存储session过滤器
 * @Date: 2019/5/2
 */
@Component
public class UserSessionHelper {
    @Autowired
    private UserAuthService userAuthService;

    /**
     * 保存用户信息到session
     */
    public void saveSafetyUser(String userName) {
        //先取，如果没有则查询用户信息，查到就放到session中
        UserAuthEntity safetyUser = LoginSessionUtils.getUser();
        if (safetyUser == null) {
            safetyUser = userAuthService.selectByUserName(userName);
            if (safetyUser != null) {
                SpringSessionUtils.setSession(LoginConstant.Session.WEBAPI_SESSION_USER, safetyUser);
            }
        }
    }
}
