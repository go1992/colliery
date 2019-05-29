package com.yw.colliery.api.business.controller;


import com.yw.colliery.entity.AqfxGkxg;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.business.impl.AqfxGkxgServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 安全风险管控-管控效果及分析 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/aqfx-gkxg")
@Api(value = "AqfxGkxgController", description = "安全风险管控-管控效果及分析")
@Slf4j
public class AqfxGkxgController extends BaseController<AqfxGkxgServiceImpl,AqfxGkxg> {
	
	
	/*@Autowired
	private IAqfxGkxgService service;
	
	@PostMapping("xm")
	public QueryChainWrapper<AqfxGkxg> xiemeng() {
		return service.query();
	}*/

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.LOW)
    public Object queryData(@RequestParam Map<String, Object> params) {
        return super.queryData(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
    public Object save(AqfxGkxg params, HttpServletRequest request) throws Exception {
        return super.save(params, request);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
    public Object saveBatch(String params) {
        return super.saveBatch(params);
    }

    @Override
    @AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
    public Object updateById(AqfxGkxg params) {
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
