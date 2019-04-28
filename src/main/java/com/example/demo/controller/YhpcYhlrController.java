package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.absys.BaseController;
import com.example.demo.absys.ESessionKey;
import com.example.demo.absys.ResultObject;
import com.example.demo.service.impl.YhpcYhlrServiceImpl;
import com.example.demo.entity.YhpcYhlr;
import com.example.demo.entity.YhpcYhlr;
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
}
