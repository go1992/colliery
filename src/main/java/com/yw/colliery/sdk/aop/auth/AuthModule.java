package com.yw.colliery.sdk.aop.auth;

import com.yw.colliery.sdk.constans.AuthConstant;

import java.lang.annotation.*;

/**
 * @Author: renzhiqiang
 * @Description: 权限模块注解
 * @Date: 2019/4/29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface AuthModule {
    /**
     * 权限id
     * @return
     */
    int authId();

    /**
     * 权限等级
     */
    int level() default AuthConstant.Level.HIGH;

    /**
     * 是否校验文件权限
     * @return
     */
    boolean fileAuth() default false;
}
