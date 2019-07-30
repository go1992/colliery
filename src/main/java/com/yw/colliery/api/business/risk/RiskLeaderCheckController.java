package com.yw.colliery.api.business.risk;


import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.entity.securityrisk.AqfxLdgz;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.risk.impl.RiskLeaderCheckServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 安全风险管控-领导跟踪检查 前端控制器
 *
 * @author xuzhou
 * @since 2019-07-29
 */
@RestController
@RequestMapping("/apiv1/aqfx-ldgz")
@Slf4j
public class RiskLeaderCheckController extends BaseController<RiskLeaderCheckServiceImpl, AqfxLdgz> {

    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.LOW)
    @PostMapping("/query")
    public Object queryData(@RequestParam Map<String, Object> params) {
        return super.commonQueryData(params);
    }

    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE)
    @PostMapping("/save")
    public Object save(AqfxLdgz params) throws Exception {
        return super.commonSave(params);
    }

    @PostMapping("/updateById")
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE)
    public Object updateById(AqfxLdgz params) {
        return super.commonUpdateById(params);
    }

    @PostMapping("/updateBatchById")
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE)
    public Object updateBatchById(String params) {
        return super.commonUpdateBatchById(params);
    }


    @PostMapping("/removeByIds")
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE)
    public Object removeByIds(String params) {
        return super.commonRemoveByIds(params);
    }
}
