package com.yw.colliery.sdk.aop;

/**
 * @Author: renzhiqiang
 * @Description: 模块认证注解
 * @Date: 2019/4/29
 */
public @interface AuthModule {
    /**
     * 模块id
     * @return
     */
    String moduleId();

    /**
     * 模块名称
     * @return
     */
    String moduleName();

}
