package com.yw.colliery.api.business.controller;


import java.lang.reflect.ParameterizedType;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.colliery.api.base.EPage;
import com.yw.colliery.api.base.ESessionKey;
import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.*;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.service.business.IXtgnQyfjService;
import com.yw.colliery.service.business.MyService;
import io.swagger.annotations.*;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.business.impl.DcSwdzZkpcServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.yw.colliery.sdk.utils.StringUtil.checkNotNull;

/**
 * <p>
 * 地测信息管理-水文地质钻孔排查 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/dc-swdz-zkpc")
@Api(value = "DcSwdzZkpcController", description = "地测信息管理-水文地质钻孔排查")
@Slf4j
public class DcSwdzZkpcController extends BaseController<DcSwdzZkpcServiceImpl,DcSwdzZkpc> {
	
	@Autowired
	public DcSwdzZkpcServiceImpl serviceImpl;
	
	@Autowired
	public DcSwdzZkpcServiceImpl zkpcServiceImpl;

	@Autowired
	public MyService myService;
	@Autowired
	private IXtgnQyfjService iXtgnQyfjService;

	
	//查询位置，让其在录入时可以下拉选择
	@GetMapping("/paichaluru")
	public JSONObject DcSwdzZkpc(){
		List<DcSwdzZkpc> DcSwdzZkpc =serviceImpl.list();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status",200);
		jsonObject.put("message","查询成功");
		jsonObject.put("data",DcSwdzZkpc);
		return jsonObject;
	}



	/*
	 * 查询
	 */
	@SuppressWarnings("unchecked")//告诉编译器忽略 unchecked 警告信息
	@ApiOperation(value = "查询数据",notes="查询",response= ResultObject.class) //
	@ApiImplicitParams
			({
					@ApiImplicitParam(
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
					//搜索
					,@ApiImplicitParam(
					value="模糊查询 like匹配 需要搜索的字段(字符串形式 逗号隔开  ['name','age','time'].join(',')"
							+ " -> 'name,age,time')",name="fields",dataType="String"
					,paramType="query",required=false,defaultValue="id"
					//,allowMultiple=true //是否为数组
			)
					,@ApiImplicitParam(value="搜索框内的值",name="searchParam",dataType="String"
					,paramType="query",required=false,defaultValue="1")

			})
	@PostMapping("/queryzkpc")
	@AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
	public Object query(@ApiParam(hidden=true)@RequestParam Map<String,Object> params
			, @ApiParam(hidden=true) HttpServletRequest request
	) {

		if(log.isDebugEnabled())log.debug("query参数:\n{}", JSON.toJSONString(params));
		HttpSession session = request.getSession();
		//分页
		String isPage = MapUtils.getString(params, "isPage",null);
		int pageNum = MapUtils.getIntValue(params, EPage.PageNum.key,EPage.PageNum.value);
		int pageSize = MapUtils.getIntValue(params, EPage.PageSize.key,EPage.PageSize.value);
		Page<DcSwdzZkpc> page = new Page<>(pageNum, pageSize);
		QueryWrapper<DcSwdzZkpc> qw = new QueryWrapper<>();

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

		//搜索
		String fields = MapUtils.getString(params, "fields",null);
		String searchParam = MapUtils.getString(params, "searchParam",null);
		if(checkNotNull(fields,searchParam)) {
			String[] fieldsArr = fields.split(",");
			qw.and((wrapper) -> {
				for(String each : fieldsArr) {
					wrapper.or().like(true, each, searchParam);
				}
				return wrapper;
			});

		}

		//区域||部门过滤
		List<String> deptsIds = (List<String>)session.getAttribute(ESessionKey.DeptsIds.key);
		qw.in(true,"ssmk", deptsIds);

		//导出
//		String isExportExcel = MapUtils.getString(params, "isExportExcel",null);
//		if(checkNotNull(isExportExcel)&&"是".equals(isExportExcel)) {
//			map.put("fileDownloadUrl", "http://www.baidu.com");
//			//不再返回数据 只返回一个下载链接供前端下载
//			return map;
//		}


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
		params.remove("isPage");
		qw.allEq(true,params,false);

		Class <DcSwdzZkpc> entityClass = (Class <DcSwdzZkpc>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		if(XtgnQyfj.class.equals(entityClass)) {
			XtgnYhlb user = (XtgnYhlb) session.getAttribute(ESessionKey.User.key);
			String deptId = user.getSsmk();
			XtgnQyfj userRootDept = iXtgnQyfjService.getById(deptId);
			List<XtgnQyfj> userDepts = null;
			if(checkNotNull(userRootDept))userDepts = iXtgnQyfjService.list(
					new QueryWrapper<XtgnQyfj>().like(true, "parentids", ","+userRootDept.getId()+","));
			if(userDepts == null) userDepts = new ArrayList<XtgnQyfj>();
			if(checkNotNull(userRootDept)) userDepts.add(userRootDept);
			userDepts.sort(Comparator.comparing(person -> person.getId()));
			return userDepts;
		}

		if(ScglScjh.class.equals(entityClass)&&"no".equals(isPage)) {
			return service.getAll(qw);
		}

		//组装返回值
//		Object result = service.page(page,qw.allEq(params));
//		return new ResultObject(ResultObject.SUCCESS,"1002","接口调用成功",result);
		//声明结果
		Map<String,Object> map = new HashMap<String,Object>();
		//查询并分页
		IPage<DcSwdzZkpc> iPage = service.page(page,qw);
		map.put("total", iPage.getTotal());
		map.put("rows", iPage.getRecords());
		map.put("dkpc",zkpcServiceImpl.getAll(qw));
		return map;
	}


	@Override
	@AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
	public Object save(DcSwdzZkpc params, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		List<String> deptsIds = (List<String>)session.getAttribute(ESessionKey.DeptsIds.key);
		params.setSsmk(deptsIds.get(0));
		return super.save(params, request);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
	public Object saveBatch(String params) {
		return super.saveBatch(params);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
	public Object updateById(DcSwdzZkpc params) {
		return super.updateById(params);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
	public Object updateBatchById(String params) {
		return super.updateBatchById(params);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.HIGH)
	public Object removeByIds(String params) {
		return super.removeByIds(params);
	}

	@Override
	@AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE, level = AuthConstant.Level.LOW)
	public Object countBy(Map<String, Object> params, HttpServletRequest request) {
		return super.countBy(params, request);
	}
}
