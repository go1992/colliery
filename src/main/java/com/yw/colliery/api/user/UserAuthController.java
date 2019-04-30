package com.yw.colliery.api.user;

import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.entity.user.UserAuthEntity;
import com.yw.colliery.sdk.response.ApiCode;
import com.yw.colliery.sdk.response.ApiResponse;
import com.yw.colliery.service.user.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author renzhiqiang
 * @Description 用户权限操作
 * @Date 2019-04-30
 **/
@RestController
@RequestMapping("/user-auth")
public class UserAuthController {
    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/select/{userId}")
    public ApiResponse selectUserById(@PathVariable Integer userId) {
        try {
            UserAuthEntity userAuthEntity = userAuthService.selectByUserId(userId);
            return ApiResponse.buildSucessResponse(userAuthEntity);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "查询用户权限失败!");
        }
    }

    @GetMapping("/select/{userName}")
    public ApiResponse selectUserById(@PathVariable String userName) {
        try {
            UserAuthEntity userAuthEntity = userAuthService.selectByUserName(userName);
            return ApiResponse.buildSucessResponse(userAuthEntity);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "查询用户权限失败!");
        }
    }

}
