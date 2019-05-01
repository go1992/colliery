package com.yw.colliery.api.auth;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.auth.AuthEntity;
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
public class AuhtController {
    @Autowired
    private AuthService authService;

    @PostMapping("/add")
    public ResultObject addAuth(@RequestBody AuthEntity authEntity) {
        try {
            int result = authService.addAuth(authEntity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("新增权限失败!");
        }
    }

    @PostMapping("/update")
    public ResultObject updateAuth(@RequestBody AuthEntity authEntity) {
        try {
            int result = authService.updateAuth(authEntity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("修改权限失败!");
        }
    }

    @GetMapping("/delete/{authId}")
    public ResultObject deleteAuth(@PathVariable Integer authId) {
        try {
            int result = authService.deleteAuth(authId);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("删除权限失败!");
        }
    }

    @GetMapping("/select/{authId}")
    public ResultObject selectAuthById(@PathVariable Integer authId) {
        try {
            AuthEntity authEntity = authService.selectById(authId);
            return ResultObject.buildSucessResponse(authEntity);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询权限失败!");
        }
    }

    @GetMapping("/select/all")
    public ResultObject selectAll() {
        try {
            List<AuthEntity> authList = authService.selectAll();
            return ResultObject.buildSucessResponse(authList);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询权限列表失败!");
        }
    }

}
