//package com.yw.colliery.api.base;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//
//public class MyWebMvcConfigurer implements WebMvcConfigurer {
//
////	@Autowired
////	private LoginHandlerInterceptor loginHandlerInterceptor;
//
//    //注册拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        registry.addInterceptor(new LoginHandlerInterceptor())
//        		//拦截
//        		.addPathPatterns("/apiv1/**")
//        		//放行
//        		//swagger
////        		.excludePathPatterns("/swagger-ui.html/**","/webjars/**","/swagger-resources/**","/v2/**")
//        		//获取验证码
//        		.excludePathPatterns("/apiv1/xtgn-yhlb/getKaptcha")
//        		//登陆
//        		.excludePathPatterns("/apiv1/xtgn-yhlb/login")
//        		//未登陆跳转页面
////        		.excludePathPatterns("/pc-ui/**")
////        		.excludePathPatterns("/img/**","/css/**", "/webjars/**")
//        		;
//    }
//
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
////		registry.addViewController("/").setViewName("/pc-ui/index.html");
//	}
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/static/**")
////                .addResourceLocations("classpath:/static/");
//    }
//
//
//}