package com.yw.colliery.api.business.controller;


import com.yw.colliery.entity.AqfxJbcsKld;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.business.impl.AqfxJbcsKldServiceImpl;
import com.yw.colliery.entity.AqfxJbcsKld;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 安全风险管控-基本参数设置-矿领导设置 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/aqfx-jbcs-kld")
@Api(value = "AqfxJbcsKldController", description = "安全风险管控-基本参数设置-矿领导设置")
@Slf4j
public class AqfxJbcsKldController extends BaseController<AqfxJbcsKldServiceImpl,AqfxJbcsKld> {
    @Override
    @AuthModule(authId = {AuthConstant.Module.SAFE_MODULE_SUPER,AuthConstant.Module.SAFE_MODULE_WATCH})
    public Object query(Map<String, Object> params, HttpServletRequest request) {
        return super.query(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE_SUPER)
    public Object save(AqfxJbcsKld params, HttpServletRequest request) throws Exception {
        return super.save(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE_SUPER)
    public Object saveBatch(String params) {
        return super.saveBatch(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE_SUPER)
    public Object updateById(AqfxJbcsKld params) {
        return super.updateById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE_SUPER)
    public Object updateBatchById(String params) {
        return super.updateBatchById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE_SUPER)
    public Object removeByIds(String params) {
        return super.removeByIds(params);
    }

    @Override
    @AuthModule(authId = {AuthConstant.Module.SAFE_MODULE_SUPER,AuthConstant.Module.SAFE_MODULE_WATCH})
    public Object countBy(Map<String, Object> params, HttpServletRequest request) {
        return super.countBy(params, request);
    }
}
