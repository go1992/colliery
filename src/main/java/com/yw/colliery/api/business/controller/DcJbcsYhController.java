package com.yw.colliery.api.business.controller;


import com.yw.colliery.entity.DcJbcsYh;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.business.impl.DcJbcsYhServiceImpl;
import com.yw.colliery.entity.DcJbcsYh;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 地测信息管理-基本参数-用户 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/dc-jbcs-yh")
@Api(value = "DcJbcsYhController", description = "地测信息管理-基本参数-用户")
@Slf4j
public class DcJbcsYhController extends BaseController<DcJbcsYhServiceImpl,DcJbcsYh> {

    @Override
    @AuthModule(authId = {AuthConstant.Module.GEODETIC_MODULE_SUPER,AuthConstant.Module.GEODETIC_MODULE_WATCH})
    public Object query(Map<String, Object> params, HttpServletRequest request) {
        return super.query(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE_SUPER)
    public Object save(DcJbcsYh params, HttpServletRequest request) throws Exception {
        return super.save(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE_SUPER)
    public Object saveBatch(String params) {
        return super.saveBatch(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE_SUPER)
    public Object updateById(DcJbcsYh params) {
        return super.updateById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE_SUPER)
    public Object updateBatchById(String params) {
        return super.updateBatchById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE_SUPER)
    public Object removeByIds(String params) {
        return super.removeByIds(params);
    }

    @Override
    @AuthModule(authId = {AuthConstant.Module.GEODETIC_MODULE_SUPER,AuthConstant.Module.GEODETIC_MODULE_WATCH})
    public Object countBy(Map<String, Object> params, HttpServletRequest request) {
        return super.countBy(params, request);
    }
}
