package com.yw.colliery.api.system.controller.role;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.role.RoleEntity;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.role.RoleService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject addRole(@RequestBody RoleEntity RoleEntity) {
        try {
            int result = roleService.addRole(RoleEntity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("新增角色失败!");
        }
    }

    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject updateRole(@RequestBody RoleEntity RoleEntity) {
        try {
            int result = roleService.updateRole(RoleEntity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("修改角色失败!");
        }
    }

    @GetMapping("/delete/{roleId}")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject deleteRole(@PathVariable Integer roleId) {
        try {
            int result = roleService.deleteRole(roleId);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("删除角色失败!");
        }
    }

    @GetMapping("/select/{roleId}")
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_WATCH, AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public ResultObject selectRoleById(@PathVariable Integer roleId) {
        try {
            RoleEntity RoleEntity = roleService.selectById(roleId);
            return ResultObject.buildSucessResponse(RoleEntity);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询角色失败!");
        }
    }

    @GetMapping("/select/all")
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_WATCH, AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public ResultObject selectAll() {
        try {
            List<RoleEntity> RoleList = roleService.selectAll();
            return ResultObject.buildSucessResponse(RoleList);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询角色列表失败!");
        }
    }
}
