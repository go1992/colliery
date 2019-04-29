package com.yw.colliery.controller;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yw.colliery.absys.BaseController;
import com.yw.colliery.absys.ResultObject;
import com.yw.colliery.entity.AqfxCsxg;
import com.yw.colliery.entity.AqfxNdfx;
import com.yw.colliery.service.IAqfxCsxgService;
import com.yw.colliery.service.impl.AqfxNdfxServiceImpl;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
	
	
}
