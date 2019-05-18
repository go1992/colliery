package com.yw.colliery.sdk.aop.auth;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.service.auth.AuthService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author renzhiqiang
 * @Description 模块认证aop
 * @Date 2019-04-29
 **/
//@Configuration
@Aspect
public class AuthModuleAop {
    @Autowired
    private AuthService authService;

    @Around("@annotation(authModule)")
    public Object auth(ProceedingJoinPoint point, AuthModule authModule) throws Throwable{
        //1.从session中获取用户
        UserRelationEntity userRelation = LoginSessionUtils.getUser();
        //2.查询权限信息
        List<Integer> authIds = Arrays.asList(ArrayUtils.toObject(authModule.authId()));
        //3.判断用户模块权限
        if (CollectionUtils.isEmpty(userRelation.getAuthList())) {
            //4.返回模块认证异常
            return ResultObject.buildFailResponse(AuthConstant.Module.NO_MODULE_AUTH);
        } else {
            //5.遍历检查模块权限
            boolean success = userRelation.getAuthList().stream().anyMatch(auth -> authIds.contains(auth.getId()));
            if (success) {
                return point.proceed();
            } else {
                //6.没有权限的话就去查询这些权限信息，方便返回给用户具体的信息
                List<AuthEntity> authList = authService.selectByIds(authIds);
                List<String> authNameList = authList.stream().map(auth -> auth.getName()).collect(Collectors.toList());
                String message = StringUtils.join(authNameList, ",");
                return ResultObject.buildFailResponse(String.format("您没有:%s", message));
            }
        }
    }
}
