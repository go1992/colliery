package com.yw.colliery.api.business.controller;


import static com.yw.colliery.api.base.MyUtil.checkNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.MapUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.api.base.EPage;
import com.yw.colliery.api.base.ESessionKey;
import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.DdxxScsg;
import com.yw.colliery.service.business.impl.DdxxScsgServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 调度信息管理-生产安全事故处理追踪 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-02
 */
@RestController
@RequestMapping("/apiv1/ddxx-scsg")
@Api(value = "DdxxScsgController", description = "调度信息管理-生产安全事故处理追踪")
@Slf4j
public class DdxxScsgController extends BaseController<DdxxScsgServiceImpl,DdxxScsg> {
	
	/*
	 * 查询
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "安全生产分析统计",notes="myCount",response=ResultObject.class)
	@ApiImplicitParams
	({
		//数据所属煤矿
		@ApiImplicitParam(
				value="所属煤矿",name="ssmk",dataType="String"
				,paramType="query",required=false,defaultValue="数据所属煤矿"
				//,allowMultiple=true //是否为数组
				)
		//日期区间
		,@ApiImplicitParam(
				value="日期选择-开始时间",name="startTime",dataType="String"
				,paramType="query",required=false,defaultValue="2000-1-1"
				//,allowMultiple=true //是否为数组
				)
		,@ApiImplicitParam(value="日期选择-结束时间",name="endTime",dataType="String"
				,paramType="query",required=false,defaultValue="2030-1-31")
		//分页
		,@ApiImplicitParam(
				value="当前页编号",name="pageNum",dataType="int"
				,paramType="query",required=false,defaultValue="1"
				//,allowMultiple=true //是否为数组
				)
		,@ApiImplicitParam(value="每页数据条数",name="pageSize",dataType="int"
				,paramType="query",required=false,defaultValue="10")
		//排序
		,@ApiImplicitParam(
				value="排序方式 asc desc",name="order",dataType="String"
				,paramType="query",required=false,defaultValue="asc"
				//,allowMultiple=true //是否为数组
				)
		,@ApiImplicitParam(value="排序字段",name="ordername",dataType="String"
				,paramType="query",required=false,defaultValue="id")
		//施工地点
		,@ApiImplicitParam(
				value="施工地点",name="sgdd",dataType="String"
				,paramType="query",required=false,defaultValue="施工地点"
				//,allowMultiple=true //是否为数组
				)
		//事故级别
		,@ApiImplicitParam(
				value="事故级别",name="sgjb",dataType="String"
				,paramType="query",required=false,defaultValue="事故级别"
				//,allowMultiple=true //是否为数组
				)
		
	})
//	@RequestMapping("/myCount")
	@PostMapping("/myCount")
	public Object myCount(@ApiParam(hidden=true)@RequestParam Map<String,Object> params
			, @ApiParam(hidden=true) HttpServletRequest request
			) {
		
		if(log.isDebugEnabled())log.debug("myCount参数:\n{}",JSON.toJSONString(params));
		HttpSession session = request.getSession();
		//分页
		int pageNum = MapUtils.getIntValue(params, EPage.PageNum.key,EPage.PageNum.value);
		int pageSize = MapUtils.getIntValue(params, EPage.PageSize.key,EPage.PageSize.value);
		IPage<Map<String,Object>> page = new Page<Map<String,Object>>(pageNum, pageSize);
		QueryWrapper<DdxxScsg> qw = new QueryWrapper<>();
		
		//日期筛选
		String startTime = MapUtils.getString(params, "startTime",null);
		String endTime = MapUtils.getString(params, "endTime",null);
		if(checkNotNull(startTime,endTime)) {
			qw.between("pcrq", startTime, endTime);
		}
		
		//排序
		String order = MapUtils.getString(params, "order",null);
		String orderKey = MapUtils.getString(params, "ordername",null);
		if(checkNotNull(order,orderKey)) {
			qw.orderBy(true, "asc".equals(order), orderKey);
		}
		
		//区域||部门过滤
		qw.in(true,"ssmk", (List<String>)session.getAttribute(ESessionKey.DeptsIds.key));
		
		//删除多余参数入库查询
		params.remove(EPage.PageNum.key);
		params.remove(EPage.PageSize.key);
		params.remove("startTime");
		params.remove("endTime");
		params.remove("order");
		params.remove("ordername");
		params.remove("fields");
		params.remove("searchParam");
		params.remove("isExportExcel");
		//部门参数由后端根据当前用户参数填充
		params.remove("ssmk");
		
		//查询条件
		qw.allEq(true,params,false);
		
		//组装返回值
//		Object result = service.page(page,qw.allEq(params));
//		return new ResultObject(ResultObject.SUCCESS,"1002","接口调用成功",result);
		//声明结果
		Map<String,Object> map = new HashMap<String,Object>();
		//查询并分页
		IPage<Map<String,Object>> iPage = service.myCount(page,qw);
		map.put("total", iPage.getTotal());
		map.put("rows", iPage.getRecords());
		return map;
		
	}
			

}
