package com.yw.colliery.api.system.controller.user;

import com.alibaba.fastjson.JSON;
import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.config.PageParam;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.request.UserRequest;
import com.yw.colliery.sdk.utils.EncodeUtils;
import com.yw.colliery.sdk.utils.ResponseUtils;
import com.yw.colliery.service.user.CollierySafetyUserService;
import com.yw.colliery.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 用户操作
 * @Date 2019-04-30
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private CollierySafetyUserService collierySafetyUserService;
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping("/add")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject addUser(@RequestBody UserRequest request) {
        CollierySafetyUserEntity entity = transfer(request);
        try {
            int result = collierySafetyUserService.addSafetyUser(entity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            logger.error("新增用户失败!", e);
            return ResultObject.buildFailResponse("新增用户失败!");
        }
    }

    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject updateUser(@RequestBody UserRequest request) {
        CollierySafetyUserEntity entity = transfer(request);
        try {
            int result = collierySafetyUserService.updateSafetyUSer(entity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            logger.error("修改用户失败!", e);
            return ResultObject.buildFailResponse("修改用户失败!");
        }
    }

    @PostMapping("/delete")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject deleteUser(@RequestBody String data) {
        try {
            List<Integer> userIds = JSON.parseArray(data, Integer.class);
            int result = collierySafetyUserService.deleteUserByIds(userIds);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            logger.error("删除用户失败!", e);
            return ResultObject.buildFailResponse("删除用户失败!");
        }
    }

    @GetMapping("/select/{userId}")
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_WATCH, AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public ResultObject selectUser(@PathVariable Integer userId) {
        try {
            CollierySafetyUserEntity result = collierySafetyUserService.selectyUserId(userId);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            logger.error("查询用户信息失败!", e);
            return ResultObject.buildFailResponse("查询用户信息失败!");
        }
    }

    @PostMapping("/select/all")
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_WATCH, AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public Object selectUserAll(@RequestBody PageParam param) {
        try {
            PageBean<CollierySafetyUserEntity> pageBean = collierySafetyUserService.selectByPage(param);
            return ResponseUtils.wrapResponse(pageBean);
        } catch (Exception e) {
            logger.error("查询所有用户信息失败!", e);
            return ResultObject.buildFailResponse("查询所有用户信息失败!");
        }
    }

    private CollierySafetyUserEntity transfer(UserRequest request) {
        CollierySafetyUserEntity entity = new CollierySafetyUserEntity();
        entity.setName(request.getName());
        //这里做个控制，防止前端回传加密后的密码过来，更新了就坏事了
        entity.setUserPwd((request.getUserPwd() != null&&(request.getUserPwd().length() < 20)) ? EncodeUtils.encode(request.getUserPwd()): null);
        entity.setId(request.getUserId());
        entity.setRoleId(request.getRoleId());
        entity.setDepartId(request.getDepartId());
        entity.setCreateUser(request.getCreateUser());
        entity.setModifyUser(request.getModifyUser());
        return entity;
    }
}
