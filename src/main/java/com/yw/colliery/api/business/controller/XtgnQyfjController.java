package com.yw.colliery.api.business.controller;


import com.yw.colliery.entity.XtgnQyfj;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.business.impl.XtgnQyfjServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 系统功能-区域分级 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
@RestController
@RequestMapping("/apiv1/xtgn-qyfj")
@Api(value = "XtgnQyfjController", description = "系统功能-区域分级")
@Slf4j
public class XtgnQyfjController extends BaseController<XtgnQyfjServiceImpl,XtgnQyfj> {
    @Override
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_SUPER,AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public Object query(Map<String, Object> params, HttpServletRequest request) {
        return super.query(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public Object save(XtgnQyfj params, HttpServletRequest request) throws Exception {
        return super.save(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public Object saveBatch(String params) {
        return super.saveBatch(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public Object updateById(XtgnQyfj params) {
        return super.updateById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public Object updateBatchById(String params) {
        return super.updateBatchById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public Object removeByIds(String params) {
        return super.removeByIds(params);
    }

    @Override
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_SUPER,AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public Object countBy(Map<String, Object> params, HttpServletRequest request) {
        return super.countBy(params, request);
    }
}
