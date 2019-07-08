package com.yw.colliery.api.business.controller;


import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.entity.DcJbcsYh;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.business.impl.DcJbcsYhServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.LOW)
    public Object queryData(@RequestParam Map<String, Object> params) {
        return super.queryData(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
    public Object save(DcJbcsYh params, HttpServletRequest request) throws Exception {
        return super.save(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
    public Object saveBatch(String params) {
        return super.saveBatch(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
    public Object updateById(DcJbcsYh params) {
        return super.updateById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
    public Object updateBatchById(String params) {
        return super.updateBatchById(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
    public Object removeByIds(String params) {
        return super.removeByIds(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
    public Object countBy(Map<String, Object> params, HttpServletRequest request) {
        return super.countBy(params, request);
    }
}
