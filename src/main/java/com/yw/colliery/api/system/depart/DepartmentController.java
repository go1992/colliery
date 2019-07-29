package com.yw.colliery.api.system.depart;

import com.alibaba.fastjson.JSON;
import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.config.BaseParam;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.request.DepartRequest;
import com.yw.colliery.sdk.utils.ResponseUtils;
import com.yw.colliery.service.depart.DepartmentService;
import com.yw.colliery.service.user.CollierySafetyUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private CollierySafetyUserService userService;

    @PostMapping("/add")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public ResultObject addDepart(@RequestBody DepartRequest request) {
        try {
            int result = departmentService.addDepart(transfer(request));
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("新增部门失败!");
        }
    }

    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public ResultObject updateDepart(@RequestBody DepartRequest request) {
        try {
            int result = departmentService.updateDepart(transfer(request));
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("修改部门失败!");
        }
    }

    @PostMapping("/delete")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public ResultObject deleteDepart(@RequestBody String data) {
        try {
            List<Integer> departIds = JSON.parseArray(data, Integer.class);
            List<CollierySafetyUserEntity> userEntities = userService.selectAllUser();
            Set<Integer> usedIdSet = userEntities.stream()
                    .map(CollierySafetyUserEntity::getDepartId)
                    .collect(Collectors.toSet());
            boolean match = departIds.stream().anyMatch(integer -> usedIdSet.contains(integer));
            if(match){
                return ResultObject.buildFailResponse("包含已经使用的部门id，无法删除！");
            }
            int result = departmentService.deleteDepartByIds(departIds);
            return ResultObject.buildSucessResponse(result);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("删除部门失败!");
        }
    }

    @GetMapping("/select/{departId}")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.LOW)
    public ResultObject selectDepartById(@PathVariable Integer departId) {
        try {
            DepartmentEntity departmentEntity = departmentService.selectById(departId);
            return ResultObject.buildSucessResponse(departmentEntity);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询部门失败!");
        }
    }

    @PostMapping("/select/all")
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.LOW)
    public Object selectAll(@RequestBody BaseParam param) {
        try {
            PageBean<DepartmentEntity> pageBean = departmentService.selectByPage(param);
            return ResponseUtils.wrapResponse(pageBean);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("查询部门列表失败!");
        }
    }

    private DepartmentEntity transfer(DepartRequest request) {
        DepartmentEntity entity = new DepartmentEntity();
        entity.setAuthIds(StringUtils.join(request.getAuthIds(), ","));
        entity.setCoalMine(request.getCoalMine());
        entity.setModifyUser(request.getModifyUser());
        entity.setCreateUser(request.getCreateUser() != null ? request.getCreateUser() : "system");
        entity.setId(request.getId());
        entity.setDepartName(request.getDepartName());
        return entity;
    }
}
