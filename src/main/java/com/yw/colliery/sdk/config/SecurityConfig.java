package com.yw.colliery.sdk.config;

import com.yw.colliery.api.system.controller.login.service.ValidationCodeFilter;
import com.yw.colliery.handler.LoginFailureHandler;
import com.yw.colliery.handler.LoginSuccessHandler;
import com.yw.colliery.sdk.constans.CollierySafetyConstant;
import com.yw.colliery.service.user.CollierySafetyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @description: 安全框架配置
 * @author: xuzhou-lhq
 * @create: 2019-04-25 11:50
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private CollierySafetyUserService collierySafetyUserService;
    @Autowired
    private ValidationCodeFilter validationCodeFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(collierySafetyUserService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().authorizeRequests()
                .antMatchers( "/css/**","/js/**","/img/**","/glng/**","/fonts/**","/apiv1/xtgn-yhlb/getKaptcha")
                .permitAll()
                .and()
                .addFilterBefore(validationCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                //指定登录页的路径
                .loginPage("/bigdatalogin.html")
                .loginProcessingUrl(CollierySafetyConstant.COLLIERY_LOGIN)
                .permitAll()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .authorizeRequests()
                // 任何请求,登录后可以访问
                .anyRequest()
                .authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                // 关闭csrf防护
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] arg){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("admin"));


    }
}
