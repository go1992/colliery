package com.yw.colliery.sdk.aop.log;

import java.lang.annotation.*;

/**
 * @Author renzhiqiang
 * @Description 日志记录注解
 * @Date 2019-05-18
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogRecord {
    LogLevel level() default LogLevel.INFO;

    String desc() default "";
}
