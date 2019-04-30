package com.yw.colliery.sdk.utils;

import com.yw.colliery.entity.user.UserAuthEntity;
import com.yw.colliery.sdk.constans.LoginConstant;

/**
 * @Author renzhiqiang
 * @Description 登录用户工具类
 * @Date 2019-04-29
 **/
public class LoginSessionUtils {
    /**
     * 获取session中的用户信息
     * @return
     */
    public static UserAuthEntity getUser() {
        return (UserAuthEntity) SpringSessionUtils.getSession(LoginConstant.Session.WEBAPI_SESSION_USER);
    }
}
