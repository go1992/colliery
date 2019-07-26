package com.yw.colliery.api.business.controller;


import com.alibaba.fastjson.JSON;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.AqfxCsxg;
import com.yw.colliery.entity.AqfxNdfx;
import com.yw.colliery.entity.securityrisk.YearsSecurityRiskEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.request.YearUnsafeRequest;
import com.yw.colliery.sdk.response.YearUnsafeResponse;
import com.yw.colliery.sdk.response.vo.UnsafeLevelVo;
import com.yw.colliery.sdk.response.vo.UnsafeTypeVo;
import com.yw.colliery.sdk.utils.ExcelUtils;
import com.yw.colliery.service.business.IAqfxCsxgService;
import com.yw.colliery.service.business.IAqfxNdfxService;
import com.yw.colliery.service.business.impl.AqfxNdfxServiceImpl;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	@Autowired
	private IAqfxNdfxService iAqfxNdfxService;
	
	/*
	 * 年度风险修改时同时插入措施修改记录
	 */
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
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
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.LOW)
	public Object queryData(@RequestParam Map<String, Object> params) {
        return super.queryData(params);
    }

	@Override
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
	public Object save(AqfxNdfx params, HttpServletRequest request) throws Exception {
		return super.save(params, request);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.HIGH)
	public Object saveBatch(String params) {
		return super.saveBatch(params);
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

	@PostMapping("/statis")
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.LOW)
	public ResultObject statis(@RequestBody YearUnsafeRequest request) {
		List<UnsafeTypeVo> unsafeTypeVos = iAqfxNdfxService.statisUnsafeTypes(request);
		List<UnsafeLevelVo> unsafeLevelVos = iAqfxNdfxService.statisUnsafeLevel(request);
		YearUnsafeResponse response = new YearUnsafeResponse(unsafeTypeVos, unsafeLevelVos);

		if (unsafeTypeVos != null && unsafeLevelVos != null) {
			return ResultObject.buildSucessResponse(response);
		} else {
			return ResultObject.buildFailResponse("没有查到年度风险统计数据");
		}
	}

	@PostMapping("/import/excel")
	@ResponseBody
	public ResultObject importExcelData(HttpServletRequest request) throws Exception{
		//获取文件
		MultipartFile file = ((MultipartHttpServletRequest)request).getFile("fileName");
		if(file == null || file.isEmpty()){
			return new ResultObject(ResultObject.FAILED,"1003","参数错误",null);
		}

		String fileName = file.getOriginalFilename().replaceAll("/", "");
		List<YearsSecurityRiskEntity> dataByExcel = ExcelUtils.getDataByExcel(file.getInputStream(), fileName, YearsSecurityRiskEntity.class);
		Integer integer = iAqfxNdfxService.saveAll(dataByExcel);
		if(integer>0){
			return new ResultObject(ResultObject.SUCCESS,"1003","数据导入成功",null);
		}
		return new ResultObject(ResultObject.SUCCESS,"1003","数据导入失败",null);
	}
}
