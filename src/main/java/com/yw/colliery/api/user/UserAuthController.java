package com.yw.colliery.api.user;

import com.yw.colliery.api.base.ResultObject;
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

    @GetMapping("/select/id/{userId}")
    public ResultObject selectUserById(@PathVariable Integer userId) {
        try {
            UserAuthEntity userAuthEntity = userAuthService.selectByUserId(userId);
            return ResultObject.buildSucessResponse(userAuthEntity);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询用户权限失败!");
        }
    }

    @GetMapping("/select/name/{userName}")
    public ResultObject selectUserById(@PathVariable String userName) {
        try {
            UserAuthEntity userAuthEntity = userAuthService.selectByUserName(userName);
            return ResultObject.buildSucessResponse(userAuthEntity);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询用户权限失败!");
        }
    }

}
