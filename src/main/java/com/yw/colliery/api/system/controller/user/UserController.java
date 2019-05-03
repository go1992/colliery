package com.yw.colliery.api.system.controller.user;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.entity.user.UserEntity;
import com.yw.colliery.sdk.request.UserRequest;
import com.yw.colliery.sdk.utils.EncodeUtils;
import com.yw.colliery.service.user.CollierySafetyUserService;
import com.yw.colliery.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/add")
    public ResultObject addUser(@RequestBody UserRequest request) {
        CollierySafetyUserEntity entity = transfer(request);
        try {
            int result = collierySafetyUserService.addSafetyUser(entity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("新增用户失败!");
        }
    }

    @PostMapping("/update")
    public ResultObject updateUser(@RequestBody UserRequest request) {
        CollierySafetyUserEntity entity = transfer(request);
        try {
            int result = collierySafetyUserService.updateSafetyUSer(entity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("修改用户失败!");
        }
    }

    @GetMapping("/delete/{userId}")
    public ResultObject deleteUser(@PathVariable Integer userId) {
        try {
            int result = collierySafetyUserService.deleteSafetyUSer(userId);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("删除用户失败!");
        }
    }

    @GetMapping("/select/{userId}")
    public ResultObject selectUser(@PathVariable Integer userId) {
        try {
            UserEntity result = userService.selectByUserId(userId);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询用户全部信息失败!");
        }
    }

    @GetMapping("/select/all")
    public ResultObject selectUserAll() {
        try {
            List<UserEntity> result = userService.selectAll();
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询所有用户信息失败!");
        }
    }

    private CollierySafetyUserEntity transfer(UserRequest request) {
        CollierySafetyUserEntity entity = new CollierySafetyUserEntity();
        entity.setUserName(request.getUserName());
        entity.setUserPwd(request.getUserPwd() != null ? EncodeUtils.encode(request.getUserPwd()): null);
        entity.setId(request.getUserId());
        entity.setRoleId(request.getRoleId());
        entity.setDepartId(request.getDepartId());
        entity.setCreateUser(request.getCreateUser());
        entity.setModifyUser(request.getModifyUser());
        return entity;
    }
}
