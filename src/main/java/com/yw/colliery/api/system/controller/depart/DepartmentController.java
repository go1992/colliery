package com.yw.colliery.api.system.controller.depart;

import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.depart.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 部门操作
 * @Date 2019-04-30
 **/
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject addDepart(@RequestBody DepartmentEntity departmentEntity) {
        try {
            int result = departmentService.addDepart(departmentEntity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("新增部门失败!");
        }
    }

    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject updateDepart(@RequestBody DepartmentEntity departmentEntity) {
        try {
            int result = departmentService.updateDepart(departmentEntity);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("修改部门失败!");
        }
    }

    @GetMapping("/delete/{departId}")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public ResultObject deleteDepart(@PathVariable Integer departId) {
        try {
            int result = departmentService.deleteDepart(departId);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("删除部门失败!");
        }
    }

    @GetMapping("/select/{departId}")
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_SUPER,AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public ResultObject selectDepartById(@PathVariable Integer departId) {
        try {
            DepartmentEntity departmentEntity = departmentService.selectById(departId);
            return ResultObject.buildSucessResponse(departmentEntity);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询部门失败!");
        }
    }

    @GetMapping("/select/all")
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_SUPER,AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public ResultObject selectAll() {
        try {
            List<DepartmentEntity> departmentEntityList = departmentService.selectAll();
            return ResultObject.buildSucessResponse(departmentEntityList);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询部门列表失败!");
        }
    }
}
