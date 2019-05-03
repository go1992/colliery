package com.yw.colliery.api.system.controller.auth;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.service.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 权限操作
 * @Date 2019-04-30
 **/
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/add")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject addAuth(@RequestBody AuthEntity authEntity) {
        try {
            int result = authService.addAuth(authEntity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("新增权限失败!");
        }
    }

    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject updateAuth(@RequestBody AuthEntity authEntity) {
        try {
            int result = authService.updateAuth(authEntity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("修改权限失败!");
        }
    }

    @GetMapping("/delete/{authId}")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject deleteAuth(@PathVariable Integer authId) {
        try {
            int result = authService.deleteAuth(authId);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("删除权限失败!");
        }
    }

    @GetMapping("/select/{authId}")
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_SUPER, AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public ResultObject selectAuthById(@PathVariable Integer authId) {
        try {
            AuthEntity authEntity = authService.selectById(authId);
            return ResultObject.buildSucessResponse(authEntity);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询权限失败!");
        }
    }

    @GetMapping("/select/all")
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_SUPER, AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public ResultObject selectAll() {
        try {
            List<AuthEntity> authList = authService.selectAll();
            return ResultObject.buildSucessResponse(authList);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询权限列表失败!");
        }
    }

    @GetMapping("/cover/{authId}")
    public ResultObject coverUserAuth(@PathVariable Integer authId) {
        try {
            UserRelationEntity userRelation = LoginSessionUtils.getUser();
            if (userRelation != null) {
                List<AuthEntity> authList = userRelation.getAuthList();
                boolean result = authList.stream().anyMatch(authEntity -> authEntity.getId().equals(authId));
                return ResultObject.buildSucessResponse(result);
            }
            return ResultObject.buildSucessResponse(false);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询用户失败!");
        }
    }
}
