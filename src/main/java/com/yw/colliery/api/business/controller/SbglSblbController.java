package com.yw.colliery.api.business.controller;


import static com.yw.colliery.api.base.MyUtil.checkNotNull;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.apache.commons.collections4.MapUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.api.base.EPage;
import com.yw.colliery.api.base.ESessionKey;
import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.SbglSblb;
import com.yw.colliery.service.business.impl.SbglSblbServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
/**
 * <p>
 * 设备管理-设备列表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
@RestController
@RequestMapping("/apiv1/sbgl-sblb")
@Api(value = "SbglSblbController", description = "设备管理-设备列表")
@Slf4j
public class SbglSblbController extends BaseController<SbglSblbServiceImpl,SbglSblb> {

	
	/*
	 * 查询
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "设备使用分析",notes="myCount",response=ResultObject.class)
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
		//排序
		,@ApiImplicitParam(
				value="排序方式 asc desc",name="order",dataType="String"
				,paramType="query",required=false,defaultValue="asc"
				//,allowMultiple=true //是否为数组
				)
		,@ApiImplicitParam(value="排序字段",name="ordername",dataType="String"
				,paramType="query",required=false,defaultValue="id")
		//使用方式
		,@ApiImplicitParam(
				value="使用方式",name="syfs",dataType="String"
				,paramType="query",required=false,defaultValue="使用方式"
				//,allowMultiple=true //是否为数组
				)
		//设备状态
		,@ApiImplicitParam(
				value="设备状态",name="sbzt",dataType="String"
				,paramType="query",required=false,defaultValue="设备状态"
				//,allowMultiple=true //是否为数组
				)
		
	})
//	@RequestMapping("/myCount")
	@PostMapping("/myCount")
	@AuthModule(moduleId = AuthConstant.Module.DEVICE_MANAGE_MODULE)
	public Object myCount(@ApiParam(hidden=true)@RequestParam Map<String,Object> params
			, @ApiParam(hidden=true) HttpServletRequest request
			) {
		
		if(log.isDebugEnabled())log.debug("myCount参数:\n{}",JSON.toJSONString(params));
		HttpSession session = request.getSession();
		QueryWrapper<SbglSblb> qw = new QueryWrapper<>();
		
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
		//查询并分页
		List<Map<String,Object>> listResult = service.myCount(qw);
		return new ResultObject(ResultObject.SUCCESS,"1008","接口调用成功",listResult);
		
	}
	
}
