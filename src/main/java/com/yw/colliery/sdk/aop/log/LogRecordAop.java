package com.yw.colliery.sdk.aop.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

/**
 * @Author renzhiqiang
 * @Description 日志记录aop
 * @Date 2019-05-18
 **/
@Configuration
@Aspect
public class LogRecordAop {
    private Logger logger = LogManager.getLogger();

    @Around("@annotation(logRecord)")
    public Object logRecord(ProceedingJoinPoint joinPoint, LogRecord logRecord)throws Throwable {
        Class clazz = joinPoint.getSignature().getDeclaringType();
        String method = joinPoint.getSignature().getName();
        Object[] params = joinPoint.getArgs();

        try {
            StopWatch watch = new StopWatch();
            watch.start();
            Object result = joinPoint.proceed();
            watch.stop();

            switch (logRecord.level()) {
                case INFO:
                    logger.info("请求类:{}, 请求方法:{}, 请求参数:{}, 耗时:{}", clazz.getName(), method, params, watch.getTotalTimeSeconds());
                    break;
                case DEBUG:
                    logger.debug("请求类:{}, 请求方法:{}, 请求参数:{}, 耗时:{}", clazz.getName(), method, params, watch.getTotalTimeSeconds());
                    break;
            }
            return result;
        } catch (Throwable e) {
            logger.error("请求类:{}, 请求方法:{}, 请求参数:{}, 发生异常！", clazz.getName(), method, params, e);
            throw e;
        }
    }
}
