package com.yw.colliery.api.user;

import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.sdk.request.UserRequest;
import com.yw.colliery.sdk.response.ApiCode;
import com.yw.colliery.sdk.response.ApiResponse;
import com.yw.colliery.sdk.utils.EncodeUtils;
import com.yw.colliery.service.user.CollierySafetyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author renzhiqiang
 * @Description 用户操作
 * @Date 2019-04-30
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CollierySafetyUserService collierySafetyUserService;

    @PostMapping("/add")
    public ApiResponse addUser(@RequestBody UserRequest request) {
        CollierySafetyUserEntity entity = transfer(request);
        try {
            int result = collierySafetyUserService.addSafetyUser(entity);
            return ApiResponse.buildSucessResponse(result);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "新增用户失败!");
        }
    }

    @PostMapping("/update")
    public ApiResponse updateUser(@RequestBody UserRequest request) {
        CollierySafetyUserEntity entity = transfer(request);
        try {
            int result = collierySafetyUserService.updateSafetyUSer(entity);
            return ApiResponse.buildSucessResponse(result);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "修改用户失败!");
        }
    }

    @GetMapping("/delete/{userId}")
    public ApiResponse deleteUser(@PathVariable Integer userId) {
        try {
            int result = collierySafetyUserService.deleteSafetyUSer(userId);
            return ApiResponse.buildSucessResponse(result);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "删除用户失败!");
        }
    }

    @GetMapping("/select/{userId}")
    public ApiResponse selectUserById(@PathVariable Integer userId) {
        try {
            CollierySafetyUserEntity result = collierySafetyUserService.selectyUserId(userId);
            return ApiResponse.buildSucessResponse(result);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "查询用户失败!");
        }
    }


    private CollierySafetyUserEntity transfer(UserRequest request) {
        CollierySafetyUserEntity entity = new CollierySafetyUserEntity();
        entity.setUserName(EncodeUtils.encode(request.getUserName()));
        entity.setUserPwd(EncodeUtils.encode(request.getPassword()));
        entity.setId(request.getUserId());
        entity.setRoleId(request.getRoleId());
        entity.setDepartId(request.getDepartId());
        entity.setCreateUser(request.getCreateUser() != null ? request.getCreateUser() : null);
        entity.setModifyDate(request.getCreateUser() != null ? null : new Date());
        entity.setModifyUser(request.getModifyUser());
        return entity;
    }
}
