package com.yw.colliery.api.role;

import com.yw.colliery.entity.role.RoleEntity;
import com.yw.colliery.sdk.response.ApiCode;
import com.yw.colliery.sdk.response.ApiResponse;
import com.yw.colliery.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Roleor renzhiqiang
 * @Description 角色操作
 * @Date 2019-04-30
 **/
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ApiResponse addRole(@RequestBody RoleEntity RoleEntity) {
        try {
            int result = roleService.addRole(RoleEntity);
            return ApiResponse.buildSucessResponse(result);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "新增角色失败!");
        }
    }

    @PostMapping("/update")
    public ApiResponse updateRole(@RequestBody RoleEntity RoleEntity) {
        try {
            int result = roleService.updateRole(RoleEntity);
            return ApiResponse.buildSucessResponse(result);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "修改角色失败!");
        }
    }

    @GetMapping("/delete")
    public ApiResponse deleteRole(@RequestParam Integer RoleId) {
        try {
            int result = roleService.deleteRole(RoleId);
            return ApiResponse.buildSucessResponse(result);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "删除角色失败!");
        }
    }

    @GetMapping("/select/{roleId}")
    public ApiResponse selectRoleById(@PathVariable Integer roleId) {
        try {
            RoleEntity RoleEntity = roleService.selectById(roleId);
            return ApiResponse.buildSucessResponse(RoleEntity);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "查询角色失败!");
        }
    }

    @GetMapping("/selectAll")
    public ApiResponse selectAll() {
        try {
            List<RoleEntity> RoleList = roleService.selectAll();
            return ApiResponse.buildSucessResponse(RoleList);
        } catch (Exception e) {
            return ApiResponse.buildResponse(ApiCode.CODE_FAIL, "查询角色列表失败!");
        }
    }
}
