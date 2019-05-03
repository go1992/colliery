package com.yw.colliery.sdk.aop;

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
    int[] authId();
}
