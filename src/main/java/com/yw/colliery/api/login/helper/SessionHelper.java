package com.yw.colliery.api.login.helper;

import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.sdk.constans.LoginConstant;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.sdk.utils.SpringSessionUtils;
import com.yw.colliery.user.CollierySafetyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author renzhiqiang
 * @Description 登录成功将用户信息放入session中
 * @Date 2019-04-30
 **/
@Component
public class SessionHelper {
    @Autowired
    private CollierySafetyUserService collierySafetyUserService;

    /**
     * 保存登录成功的用户信息到session
     * @param userName
     */
    public void saveSafetyUser(String userName){
        //先取，如果没有则查询用户信息，查到就放到session中
        CollierySafetyUserEntity safetyUser = LoginSessionUtils.getUser();
        if (safetyUser == null) {
            safetyUser = collierySafetyUserService.selectByUserCode(userName);
            if (safetyUser != null) {
                SpringSessionUtils.setSession(LoginConstant.Session.WEBAPI_SESSION_USER, safetyUser);
            }
        }
    }
}
