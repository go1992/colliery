package com.yw.colliery.api.system.location;


import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.entity.XtgnQyfj;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.location.impl.LocationLevelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@Slf4j
public class LocationLevelController extends BaseController<LocationLevelServiceImpl,XtgnQyfj> {


    @AuthModule(authId = AuthConstant.Module.SYSTEM_MODULE, level = AuthConstant.Level.LOW)
    @PostMapping("/query")
    public Object queryData(@RequestParam Map<String, Object> params) {
        return super.commonQueryData(params);
    }
}
