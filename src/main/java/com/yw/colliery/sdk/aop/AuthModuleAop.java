package com.yw.colliery.sdk.aop;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.entity.user.UserAuthEntity;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.exception.AuthException;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author renzhiqiang
 * @Description 模块认证aop
 * @Date 2019-04-29
 **/
@Configuration
@Aspect
public class AuthModuleAop {

    @Around("@annotation(authModule)")
    public Object auth(ProceedingJoinPoint point, AuthModule authModule) throws Throwable{
        //1.从session中获取用户
        UserAuthEntity userAuthEntity = LoginSessionUtils.getUser();
        //2.判断用户模块权限
        if (userAuthEntity == null || CollectionUtils.isEmpty(userAuthEntity.getAuthList())) {
            //3.返回模块认证异常
            return ResultObject.buildFailResponse(AuthConstant.Module.NO_MODULE_AUTH);
        } else {
            //4.遍历检查模块权限
            List<Integer> authors = userAuthEntity.getAuthList().stream().map(userAuth -> userAuth.getId())
                    .collect(Collectors.toList());
            boolean success = authors.stream().anyMatch(author -> author == authModule.authId());
            if (success) {
                return point.proceed();
            } else {
                return ResultObject.buildFailResponse(AuthConstant.Module.NO_MODULE_AUTH);
            }
        }
    }

}
