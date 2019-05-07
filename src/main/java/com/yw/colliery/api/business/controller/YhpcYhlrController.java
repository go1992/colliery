package com.yw.colliery.api.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.api.base.ESessionKey;
import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.YhpcYhlr;
import com.yw.colliery.entity.YhpcYhlr;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.business.impl.YhpcYhlrServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 隐患排查治理-隐患录入 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/yhpc-yhlr")
@Api(value = "YhpcYhlrController", description = "隐患排查治理-隐患录入及分发")
@Slf4j
public class YhpcYhlrController extends BaseController<YhpcYhlrServiceImpl,YhpcYhlr> {
	
	@ApiOperation(value = "决策统计",response=ResultObject.class)
	@PostMapping("/countToPieChart")
	@AuthModule(authId = {AuthConstant.Module.UNSAFE_MODULE_SUPER,AuthConstant.Module.UNSAFE_MODULE_WATCH})
	public Object countBy(@ApiParam(hidden=true) @RequestParam Map<String,Object> params
			, @ApiParam(hidden=true) HttpServletRequest request
			) {

		if(log.isDebugEnabled())log.debug("countBy参数:\n{}",params);
		HttpSession session = request.getSession();
		Map<String,Object> result = new HashMap<String,Object>();
		
		QueryWrapper<YhpcYhlr> qw = null;
		
		//用户区域部门列表sql ('数据所属煤矿','所属煤矿')
		@SuppressWarnings("unchecked")
		List<String> deptsIds = (List<String>)session.getAttribute(ESessionKey.DeptsIds.key);
		
		qw = new QueryWrapper<YhpcYhlr>();
		qw.select("count(*) AS shuliang","yhlx");
		qw.in(true,"ssmk", deptsIds);
		qw.groupBy(true, "yhlx");
		result.put("yhlxGroup", service.list(qw));
		
		qw = new QueryWrapper<YhpcYhlr>();
		qw.select("count(*) AS shuliang","yhjb");
		qw.in(true,"ssmk", deptsIds);
		qw.groupBy(true, "yhjb");
		result.put("yhjbGroup", service.list(qw));

		qw = new QueryWrapper<YhpcYhlr>();
		qw.select("count(*) AS shuliang","yhzt");
		qw.in(true,"ssmk", deptsIds);
		qw.groupBy(true, "yhzt");
		result.put("yhztGroup", service.list(qw));

		return new ResultObject(ResultObject.SUCCESS,"1005","接口调用成功",result);
	}
	
	
	/*@ApiOperation(value = "隐患分发",response =ResultObject.class)
	@PostMapping("/fenfa")
	public Object fenfa(@) {
		
	}*/


	@Override
	@AuthModule(authId = {AuthConstant.Module.UNSAFE_MODULE_SUPER,AuthConstant.Module.UNSAFE_MODULE_WATCH})
	public Object query(Map<String, Object> params, HttpServletRequest request) {
		return super.query(params, request);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
	public Object save(YhpcYhlr params, HttpServletRequest request) throws Exception {
		return super.save(params, request);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
	public Object saveBatch(String params) {
		return super.saveBatch(params);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
	public Object updateById(YhpcYhlr params) {
		return super.updateById(params);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
	public Object updateBatchById(String params) {
		return super.updateBatchById(params);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
	public Object removeByIds(String params) {
		return super.removeByIds(params);
	}

}
