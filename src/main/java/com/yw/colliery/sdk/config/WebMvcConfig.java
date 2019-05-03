package com.yw.colliery.sdk.config;

import com.yw.colliery.sdk.interceptor.UserSessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: renzhiqiang
 * @Description: springboot2.x跨域配置
 * @Date: 2019/4/29
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UserSessionInterceptor userSessionInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(3600)
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(userSessionInterceptor);
    }
}
