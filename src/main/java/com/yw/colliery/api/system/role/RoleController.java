package com.yw.colliery.api.system.role;

import com.alibaba.fastjson.JSON;
import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.entity.role.RoleEntity;
import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.config.BaseParam;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.ResponseUtils;
import com.yw.colliery.service.role.RoleService;
import com.yw.colliery.service.user.CollierySafetyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private CollierySafetyUserService userService;

    @PostMapping("/add")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public ResultObject addRole(@RequestBody RoleEntity RoleEntity) {
        try {
            int result = roleService.addRole(RoleEntity);
            return ResultObject.buildSuccessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("新增角色失败!");
        }
    }

    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public ResultObject updateRole(@RequestBody RoleEntity RoleEntity) {
        try {
            int result = roleService.updateRole(RoleEntity);
            return ResultObject.buildSuccessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("修改角色失败!");
        }
    }

    @PostMapping("/delete")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public ResultObject deleteRole(@RequestBody String data) {
        try {
            List<Integer> roleIds = JSON.parseArray(data, Integer.class);
            List<CollierySafetyUserEntity> userEntities = userService.selectAllUser();
            Set<Integer> collect = userEntities.stream().map(CollierySafetyUserEntity::getRoleId).collect(Collectors.toSet());
            boolean match = roleIds.stream().anyMatch(integer -> collect.contains(integer));
            if(match){
                return ResultObject.buildFailResponse("包含已经使用的角色id，不能删除！");
            }
            int result = roleService.deleteRoleByIds(roleIds);
            return ResultObject.buildSuccessResponse(result);
        } catch (Exception e) {
            log.error("删除角色异常",e);
            return ResultObject.buildFailResponse("删除角色失败!");
        }
    }

    @GetMapping("/select/{roleId}")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.LOW)
    public ResultObject selectRoleById(@PathVariable Integer roleId) {
        try {
            RoleEntity RoleEntity = roleService.selectById(roleId);
            return ResultObject.buildSuccessResponse(RoleEntity);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询角色失败!");
        }
    }

    @PostMapping("/select/all")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.LOW)
    public Object selectAll(@RequestBody BaseParam param) {
        try {
            PageBean<RoleEntity> pageBean = roleService.selectByPage(param);
            return ResponseUtils.wrapResponse(pageBean);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询角色列表失败!");
        }
    }
}
