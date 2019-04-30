package com.yw.colliery.sdk.aop;

import java.lang.annotation.*;

/**
 * @Author: renzhiqiang
 * @Description: 模块认证注解
 * @Date: 2019/4/29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
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
    String moduleName() default "暂不支持查询模块名称";
}
