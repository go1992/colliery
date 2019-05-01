package com.yw.colliery.api.auth;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.sdk.response.ApiCode;
import com.yw.colliery.sdk.response.ApiResponse;
import com.yw.colliery.service.auth.AuthService;
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
public class AuhtController {
    @Autowired
    private AuthService authService;

    @PostMapping("/add")
    public ApiResponse addAuth(@RequestBody AuthEntity authEntity) {
        try {
            int result = authService.addAuth(authEntity);
            return ApiResponse.buildSucessResponse(result);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "新增权限失败!");
        }
    }

    @PostMapping("/update")
    public ApiResponse updateAuth(@RequestBody AuthEntity authEntity) {
        try {
            int result = authService.updateAuth(authEntity);
            return ApiResponse.buildSucessResponse(result);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "修改权限失败!");
        }
    }

    @GetMapping("/delete/{authId}")
    public ApiResponse deleteAuth(@PathVariable Integer authId) {
        try {
            int result = authService.deleteAuth(authId);
            return ApiResponse.buildSucessResponse(result);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "删除权限失败!");
        }
    }

    @GetMapping("/select/{authId}")
    public ApiResponse selectAuthById(@PathVariable Integer authId) {
        try {
            AuthEntity authEntity = authService.selectById(authId);
            return ApiResponse.buildSucessResponse(authEntity);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "查询权限失败!");
        }
    }

    @GetMapping("/select/all")
    public ApiResponse selectAll() {
        try {
            List<AuthEntity> authList = authService.selectAll();
            return ApiResponse.buildSucessResponse(authList);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "查询权限列表失败!");
        }
    }

}
