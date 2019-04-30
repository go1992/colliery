package com.yw.colliery.api.business.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.api.base.ESessionKey;
import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.WxyglSjlb;
import com.yw.colliery.service.business.impl.WxyglSjlbServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 危险源管理-数据列表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-18
 */
@RestController
@RequestMapping("/apiv1/wxygl-sjlb")
@Api(value = "WxyglSjlbController", description = "危险源管理-数据列表")
@Slf4j
public class WxyglSjlbController extends BaseController<WxyglSjlbServiceImpl,WxyglSjlb> {

	@ApiOperation(value = "决策统计",response=ResultObject.class)
	@PostMapping("/countToPieChart")
	@AuthModule(moduleId = AuthConstant.Module.DANGER_MANAGE_MODULE)
	public Object countBy(@ApiParam(hidden=true) @RequestParam Map<String,Object> params
			, @ApiParam(hidden=true) HttpServletRequest request
			) {

		if(log.isDebugEnabled())log.debug("countBy参数:\n{}",params);
		HttpSession session = request.getSession();
		Map<String,Object> result = new HashMap<String,Object>();
		
		QueryWrapper<WxyglSjlb> qw = null;
		
		//用户区域部门列表sql ('数据所属煤矿','所属煤矿')
		@SuppressWarnings("unchecked")
		List<String> deptsIds = (List<String>)session.getAttribute(ESessionKey.DeptsIds.key);
		
		qw = new QueryWrapper<WxyglSjlb>();
		qw.select("count(*) AS shuliang","qymc");
		qw.in(true,"ssmk", deptsIds);
		qw.groupBy(true, "qymc");
		result.put("qymcGroup", service.list(qw));
		
		qw = new QueryWrapper<WxyglSjlb>();
		qw.select("count(*) AS shuliang","zdwxydj");
		qw.in(true,"ssmk", deptsIds);
		qw.groupBy(true, "zdwxydj");
		result.put("zdwxydjGroup", service.list(qw));

		qw = new QueryWrapper<WxyglSjlb>();
		qw.select("count(*) AS shuliang","zdwxylx");
		qw.in(true,"ssmk", deptsIds);
		qw.groupBy(true, "zdwxylx");
		result.put("zdwxylxGroup", service.list(qw));

		return new ResultObject(ResultObject.SUCCESS,"1005","接口调用成功",result);
	}
	
}


















































