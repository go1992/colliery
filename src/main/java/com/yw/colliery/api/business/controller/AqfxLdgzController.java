package com.yw.colliery.api.business.controller;


import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.entity.AqfxLdgz;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.business.impl.AqfxLdgzServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 安全风险管控-领导跟踪检查 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/aqfx-ldgz")
@Api(value = "AqfxLdgzController", description = "安全风险管控-领导跟踪检查")
@Slf4j
public class AqfxLdgzController extends BaseController<AqfxLdgzServiceImpl,AqfxLdgz> {

    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.LOW)
    @PostMapping("/query")
    public Object queryData(@RequestParam Map<String, Object> params) {
        return super.commonQueryData(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
    public Object save(AqfxLdgz params, HttpServletRequest request) throws Exception {
        return super.save(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
    public Object saveBatch(String params) {
        return super.saveBatch(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
    public Object updateById(AqfxLdgz params) {
        return super.updateById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
    public Object updateBatchById(String params) {
        return super.updateBatchById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
    public Object removeByIds(String params) {
        return super.removeByIds(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.LOW)
    public Object countBy(Map<String, Object> params, HttpServletRequest request) {
        return super.countBy(params, request);
    }
}
