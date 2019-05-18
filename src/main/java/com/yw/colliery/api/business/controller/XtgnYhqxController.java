package com.yw.colliery.api.business.controller;


import com.yw.colliery.entity.XtgnYhqx;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.business.impl.XtgnYhqxServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 系统功能-用户权限 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-09
 */
@RestController
@RequestMapping("/apiv1/xtgn-yhqx")
@Api(value = "XtgnYhqxController", description = "系统功能-用户权限")
@Slf4j
public class XtgnYhqxController extends BaseController<XtgnYhqxServiceImpl,XtgnYhqx> {
    @Override
    @AuthModule(authId = {AuthConstant.Module.SYSTEM_MODULE_SUPER,AuthConstant.Module.SYSTEM_MODULE_WATCH})
    public Object query(Map<String, Object> params, HttpServletRequest request) {
        return super.query(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public Object save(XtgnYhqx params, HttpServletRequest request) throws Exception {
        return super.save(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public Object saveBatch(String params) {
        return super.saveBatch(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE_SUPER)
    public Object updateById(XtgnYhqx params) {
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
