package com.yw.colliery.api.business.controller;


import java.util.Date;
import java.util.Map;

import com.yw.colliery.entity.AqfxNdfx;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.AqfxCsxg;
import com.yw.colliery.entity.AqfxNdfx;
import com.yw.colliery.service.business.IAqfxCsxgService;
import com.yw.colliery.service.business.impl.AqfxNdfxServiceImpl;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 安全风险管控-年度风险辨识 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-02
 */
@RestController
@RequestMapping("/apiv1/aqfx-ndfx")
@Api(value = "AqfxNdfxController", description = "安全风险管控-年度风险辨识")
@Slf4j
public class AqfxNdfxController extends BaseController<AqfxNdfxServiceImpl,AqfxNdfx> {

	@Autowired
	private IAqfxCsxgService iAqfxCsxgService;
	
	/*
	 * 年度风险修改时同时插入措施修改记录
	 */
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE_SUPER)
	public Object updateById(AqfxNdfx params) {
		if(log.isDebugEnabled())log.debug("updateById参数:\n{}",JSON.toJSONString(params));
		AqfxNdfx old = service.getById(params.getId());
		Object result = service.updateById(params);
		if(!old.getGkcs().equals(params.getGkcs())){
			AqfxCsxg oldSave = JSON.parseObject(JSON.toJSONString(old), AqfxCsxg.class);
			oldSave.setXgrq(new Date());
			oldSave.setYsid(old.getId());
			oldSave.setCsbh(params.getGkcs());
			iAqfxCsxgService.save(oldSave);
		}
		return new ResultObject(ResultObject.SUCCESS,"1004","接口调用成功",result);
	}

	@Override
	@AuthModule(authId = {AuthConstant.Module.SAFE_MODULE_SUPER,AuthConstant.Module.SAFE_MODULE_WATCH})
	public Object query(Map<String, Object> params, HttpServletRequest request) {
		return super.query(params, request);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE_SUPER)
	public Object save(AqfxNdfx params, HttpServletRequest request) throws Exception {
		return super.save(params, request);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE_SUPER)
	public Object saveBatch(String params) {
		return super.saveBatch(params);
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
