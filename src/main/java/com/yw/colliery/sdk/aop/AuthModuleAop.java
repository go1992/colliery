package com.yw.colliery.sdk.aop;

import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.exception.AuthException;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * @Author renzhiqiang
 * @Description 模块认证aop
 * @Date 2019-04-29
 **/
@Aspect
public class AuthModuleAop {
    private Logger logger = LoggerFactory.getLogger(AuthModuleAop.class);

    @Around("@annotation(authModule)")
    public Object auth(ProceedingJoinPoint point, AuthModule authModule) throws Throwable{
        //1.从session获取用户
        CollierySafetyUserEntity safetyUser = LoginSessionUtils.getUser();
        //2.判断用户模块权限
        Collection<? extends GrantedAuthority> collection = safetyUser.getAuthorities();
        if (CollectionUtils.isEmpty(collection)) {
            //3.返回模块认证异常
            throw new AuthException(AuthException.AuthEnum.MODULE, AuthConstant.Module.NO_MODULE);
        } else {
            //4.遍历检查模块权限
//            collection.stream().anyMatch(author -> author)
        }

        return null;
    }

}
