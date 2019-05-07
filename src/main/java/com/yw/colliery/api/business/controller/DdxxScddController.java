package com.yw.colliery.api.business.controller;


import com.yw.colliery.entity.DdxxScdd;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.business.impl.DdxxScddServiceImpl;
import com.yw.colliery.entity.DdxxScdd;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 调度信息管理-安全生产调度报表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/ddxx-scdd")
@Api(value = "DdxxScddController", description = "调度信息管理-安全生产调度报表")
@Slf4j
public class DdxxScddController extends BaseController<DdxxScddServiceImpl,DdxxScdd> {
    @Override
    @AuthModule(authId = {AuthConstant.Module.DISPATCH_MODULE_SUPER,AuthConstant.Module.DISPATCH_MODULE_WATCH})
    public Object query(Map<String, Object> params, HttpServletRequest request) {
        return super.query(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE_SUPER)
    public Object save(DdxxScdd params, HttpServletRequest request) throws Exception {
        return super.save(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE_SUPER)
    public Object saveBatch(String params) {
        return super.saveBatch(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE_SUPER)
    public Object updateById(DdxxScdd params) {
        return super.updateById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE_SUPER)
    public Object updateBatchById(String params) {
        return super.updateBatchById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE_SUPER)
    public Object removeByIds(String params) {
        return super.removeByIds(params);
    }

    @Override
    @AuthModule(authId = {AuthConstant.Module.DISPATCH_MODULE_SUPER,AuthConstant.Module.DISPATCH_MODULE_WATCH})
    public Object countBy(Map<String, Object> params, HttpServletRequest request) {
        return super.countBy(params, request);
    }
}
