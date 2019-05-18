package com.yw.colliery.api.business.controller;


import com.yw.colliery.entity.DdxxJbScdw;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.business.impl.DdxxJbScdwServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 调度信息管理-基本信息设置-生产单位设置 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/ddxx-jb-scdw")
@Api(value = "DdxxJbScdwController", description = "调度信息管理-基本信息设置-生产单位设置")
@Slf4j
public class DdxxJbScdwController extends BaseController<DdxxJbScdwServiceImpl,DdxxJbScdw> {
    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE, level = AuthConstant.Level.LOW)
    public Object query(Map<String, Object> params, HttpServletRequest request) {
        return super.query(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE, level = AuthConstant.Level.HIGH)
    public Object save(DdxxJbScdw params, HttpServletRequest request) throws Exception {
        return super.save(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE, level = AuthConstant.Level.HIGH)
    public Object saveBatch(String params) {
        return super.saveBatch(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE, level = AuthConstant.Level.HIGH)
    public Object updateById(DdxxJbScdw params) {
        return super.updateById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE, level = AuthConstant.Level.HIGH)
    public Object updateBatchById(String params) {
        return super.updateBatchById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE, level = AuthConstant.Level.HIGH)
    public Object removeByIds(String params) {
        return super.removeByIds(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE, level = AuthConstant.Level.LOW)
    public Object countBy(Map<String, Object> params, HttpServletRequest request) {
        return super.countBy(params, request);
    }
}
