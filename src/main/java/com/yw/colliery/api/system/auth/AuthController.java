package com.yw.colliery.api.system.auth;

import com.alibaba.fastjson.JSON;
import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.aop.log.LogLevel;
import com.yw.colliery.sdk.aop.log.LogRecord;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.config.BaseParam;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.sdk.utils.ResponseUtils;
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
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public ResultObject addAuth(@RequestBody AuthEntity authEntity) {
        try {
            int result = authService.addAuth(authEntity);
            return ResultObject.buildSuccessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("新增权限失败!");
        }
    }

    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public ResultObject updateAuth(@RequestBody AuthEntity authEntity) {
        try {
            int result = authService.updateAuth(authEntity);
            return ResultObject.buildSuccessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("修改权限失败!");
        }
    }

    @PostMapping("/delete")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public ResultObject deleteAuth(@PathVariable String data) {
        try {
            List<Integer> authIds = JSON.parseArray(data, Integer.class);
            int result = authService.deleteAuthByIds(authIds);
            return ResultObject.buildSuccessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("删除权限失败!");
        }
    }

    @GetMapping("/select/{authId}")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.LOW)
    @LogRecord(level = LogLevel.INFO)
    public ResultObject selectAuthById(@PathVariable Integer authId) {
        try {
            AuthEntity authEntity = authService.selectById(authId);
            return ResultObject.buildSuccessResponse(authEntity);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询权限失败!");
        }
    }

    @PostMapping("/select/all")

    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.LOW)
    public Object selectAll(@RequestBody BaseParam param) {
        try {
            PageBean<AuthEntity> pageBean = authService.selectByPage(param);
            return ResponseUtils.wrapResponse(pageBean);
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
                boolean result = authList.stream().anyMatch(authEntity -> authEntity.getId() == authId);
                return ResultObject.buildSuccessResponse(result);
            }
            return ResultObject.buildSuccessResponse(false);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询该用户在当前模块的权限失败!");
        }
    }
}
