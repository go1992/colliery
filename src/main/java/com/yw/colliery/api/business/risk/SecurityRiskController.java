package com.yw.colliery.api.business.risk;


import com.alibaba.fastjson.JSON;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.entity.securityrisk.AqfxCsxg;
import com.yw.colliery.entity.securityrisk.AqfxNdfx;
import com.yw.colliery.entity.securityrisk.YearsSecurityRiskEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.request.YearUnsafeRequest;
import com.yw.colliery.sdk.response.YearUnsafeResponse;
import com.yw.colliery.sdk.response.vo.UnsafeLevelVo;
import com.yw.colliery.sdk.response.vo.UnsafeTypeVo;
import com.yw.colliery.sdk.utils.ExcelUtils;
import com.yw.colliery.service.location.IAqfxCsxgService;
import com.yw.colliery.service.risk.RiskService;
import com.yw.colliery.service.risk.impl.RiskServiceImpl;
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
public class SecurityRiskController extends BaseController<RiskServiceImpl,AqfxNdfx> {

	@Autowired
	private IAqfxCsxgService iAqfxCsxgService;
	@Autowired
	private RiskService riskService;

	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.LOW)
	@PostMapping("/query")
	public Object queryData(@RequestParam Map<String, Object> params) {
		return super.commonQueryData(params);
	}

	/**
	 * 年度风险修改时同时插入措施修改记录
	 *
	 */
	@PostMapping("/updateById")
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE)
	public Object updateById(AqfxNdfx params) {
		log.debug("updateById参数:\n{}",JSON.toJSONString(params));
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



	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE)
	@PostMapping("/save")
	public Object save(AqfxNdfx params) throws Exception {
		return super.commonSave(params);
	}

	@PostMapping("/removeByIds")
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE)
	public Object removeByIds(String params) {
		return super.commonRemoveByIds(params);
	}


	@PostMapping("/statis")
	@AuthModule(authId = AuthConstant.Module.SAFE_MODULE, level = AuthConstant.Level.LOW)
	public ResultObject statis(@RequestBody YearUnsafeRequest request) {
		List<UnsafeTypeVo> unsafeTypeVos = riskService.statisUnsafeTypes(request);
		List<UnsafeLevelVo> unsafeLevelVos = riskService.statisUnsafeLevel(request);
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
		Integer integer = riskService.saveAll(dataByExcel);
		if(integer>0){
			return new ResultObject(ResultObject.SUCCESS,"1003","数据导入成功",null);
		}
		return new ResultObject(ResultObject.SUCCESS,"1003","数据导入失败",null);
	}
}
