package com.yw.colliery.api.business.controller;


import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.entity.XtgnQxlb;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.business.impl.XtgnQxlbServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 系统功能-权限列表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
@RestController
@RequestMapping("/apiv1/xtgn-qxlb")
@Api(value = "XtgnQxlbController", description = "系统功能-权限列表")
@Slf4j
public class XtgnQxlbController extends BaseController<XtgnQxlbServiceImpl,XtgnQxlb> {
    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.LOW)
    public Object queryData(@RequestParam Map<String, Object> params) {
        return super.queryData(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public Object save(XtgnQxlb params, HttpServletRequest request) throws Exception {
        return super.save(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public Object saveBatch(String params) {
        return super.saveBatch(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public Object updateById(XtgnQxlb params) {
        return super.updateById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public Object updateBatchById(String params) {
        return super.updateBatchById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.HIGH)
    public Object removeByIds(String params) {
        return super.removeByIds(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.LOW)
    public Object countBy(Map<String, Object> params, HttpServletRequest request) {
        return super.countBy(params, request);
    }
}
