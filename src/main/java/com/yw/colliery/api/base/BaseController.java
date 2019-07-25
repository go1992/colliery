package com.yw.colliery.api.base;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.colliery.entity.*;
import com.yw.colliery.sdk.utils.SpringSessionUtils;
import com.yw.colliery.service.business.IXtgnQyfjService;
import com.yw.colliery.service.business.MyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import static com.yw.colliery.api.base.MyUtil.checkNotNull;

@Slf4j
public abstract class BaseController<S extends ServiceImpl<?, T>,T>{
	
	//com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
	@Autowired
	public S service;
	@Autowired
	public MyService myService;
	@Autowired
	private IXtgnQyfjService iXtgnQyfjService;
	
	
	/*
	 * 全局异常统一处理
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public Object AuthenticationException(Exception e) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		e.printStackTrace();
		log.error("本次接口调用异常,异常编号{}",uuid,e);
		return new ResultObject(ResultObject.FAILED,"1001","本次接口调用异常,异常编号",uuid);
	}
	
	/*
	 * 查询
	 */
	@PostMapping("/query")
	public Object queryData(@RequestParam Map<String,Object> params
			) {
		if(log.isDebugEnabled())log.debug("query参数:\n{}",JSON.toJSONString(params));
		HttpSession session = SpringSessionUtils.getRequest().getSession();
		//分页
		String isPage = MapUtils.getString(params, "isPage",null);
		int pageNum = MapUtils.getIntValue(params, EPage.PageNum.key,EPage.PageNum.value);
		int pageSize = MapUtils.getIntValue(params, EPage.PageSize.key,EPage.PageSize.value);
		Page<T> page = new Page<>(pageNum, pageSize);
		QueryWrapper<T> qw = new QueryWrapper<>();
		
		//日期筛选
		String startTime = MapUtils.getString(params, "startTime",null);
		String endTime = MapUtils.getString(params, "endTime",null);
		if(checkNotNull(startTime,endTime)) {
			qw.between("identificationDate", startTime, endTime);
		}

		String dateType = MapUtils.getString(params, "datetype",null);
		if (StringUtils.isNotEmpty(dateType)) {
			qw.eq("datetype", dateType);
		}

		String id = MapUtils.getString(params, "id",null);
		if (StringUtils.isNotEmpty(id)) {
			qw.eq("id", Long.valueOf(id));
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
		qw.in(true,"coalMineName", deptsIds);
		
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
		params.remove("coalMineName");
		params.remove("isPage");
		qw.allEq(true,params,false);
		
		Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
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
			return service.list(qw);
		}
		
		//组装返回值
//		Object result = service.page(page,qw.allEq(params));
//		return new ResultObject(ResultObject.SUCCESS,"1002","接口调用成功",result);
		//声明结果
		Map<String,Object> map = new HashMap<String,Object>();
		//查询并分页
		IPage<T> iPage = service.page(page,qw);
		map.put("total", iPage.getTotal());
		map.put("rows", iPage.getRecords());
		return map;
	}

	/*
	 * 新增
	 */
	@ApiOperation(value = "新增数据",notes="新增",response=ResultObject.class)
	@PostMapping("/save")
	public Object save(
			T params
			, @ApiParam(hidden=true) HttpServletRequest request
			) throws Exception {
		if(log.isDebugEnabled())log.debug("save参数:\n{}",JSON.toJSONString(params));
		HttpSession session = request.getSession();
		//生产管理-实际产量自动填充时间
		@SuppressWarnings("unchecked")
		Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		//数据所属部门 由后端从session取出用户信息 存入的值为用户的ssmk 判断T是否有ssmk字段 有自动填充值 不由前端设置 
		//获取成员变量列表
		//判断是否包含ssmk字段 有自动填充值
		Field[] fields = entityClass.getDeclaredFields();
		XtgnYhlb user = (XtgnYhlb) session.getAttribute(ESessionKey.User.key);
		if(!XtgnYhlb.class.equals(entityClass)&&!XtgnQyfj.class.equals(entityClass))for(Field each : fields) {
			each.setAccessible(true);
			if("coalMineName".equals(each.getName())) {
				if(!checkNotNull(user.getSsmk()))throw new RuntimeException("用户所属部门为空,数据无法录入");
				each.set(params, user.getSsmk());
			}
		}
		if(ScglSjcl.class.equals(entityClass)) {
//			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			ScglSjcl scglSjclObj = (ScglSjcl) params;
			scglSjclObj.setPcrq(new Date());
		}
		Object result = service.save(params);
		return new ResultObject(ResultObject.SUCCESS,"1003","接口调用成功",result);
	}
	
	/*
	 * 批量新增 (可修改为批量新增或修改 saveOrUpdateByBatch 但是前端必须在生成Excel时带上id 用户编辑excel后导入 前端转为数组的json字符串发给后端)
	 */
	@ApiOperation(value = "批量新增据数据",notes="批量新增",response=ResultObject.class)
	@ApiImplicitParams
	({
		@ApiImplicitParam(value="新增数据数组json序列化字符串",dataType="String",
				name="params",paramType="query",required=true,defaultValue="[{\"coalMineName\":\"一号煤矿\"},{\"coalMineName\":\"二号煤矿\"}]")
	})
	@PostMapping("/saveBatch")
	public Object saveBatch(String params) {
		if(log.isDebugEnabled())log.debug("save参数:\n{}",params);
		@SuppressWarnings("unchecked")
		Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		List<T> entities = JSON.parseArray(params, entityClass);
		Object result = service.saveBatch(entities);
		return new ResultObject(ResultObject.SUCCESS,"1006","接口调用成功",result);
	}
	
	/*
	 * 修改
	 */
	@ApiOperation(value = "修改数据",notes="修改",response=ResultObject.class)
	@PostMapping("/updateById")
	public Object updateById(T params) {
		if(log.isDebugEnabled())log.debug("updateById参数:\n{}",JSON.toJSONString(params));
		Object result = service.updateById(params);
		return new ResultObject(ResultObject.SUCCESS,"1004","接口调用成功",result);
	}
	
	/*
	 * 批量修改
	 */
	@ApiOperation(value = "批量修改数据",notes="批量修改",response=ResultObject.class)
	@ApiImplicitParams
	({
		@ApiImplicitParam(value="批量修改数据数组json序列化字符串",dataType="String",
				name="params",paramType="query",required=true,defaultValue="[{\"id\":10,\"identificationDate\":\"2012-12-26\",\"coalMineName\":\"3\",\"reservedField1\":\"预留字段a\",\"ylzdb\":\"预留字段b\",\"ylzdc\":\"预留字段c\",\"fxd\":\"风险点\",\"dtwzdd\":\"地图位置定点\",\"zrdw\":\"责任单位\",\"fxdj\":\"风险等级\",\"fxfl\":\"风险分类\",\"xzrs\":\"限制人数123\",\"fxms\":\"风险描述\",\"gkcs\":\"3方法\",\"zyfzr\":\"主要负责人\",\"fgfzr\":\"分管负责人\",\"fxlx\":\"类型(年度风险,专项风险)\",\"xgrq\":null,\"csbh\":\"3f'f\",\"ysid\":1},{\"id\":11,\"identificationDate\":\"2012-12-26\",\"coalMineName\":\"3\",\"reservedField1\":\"预留字段a\",\"ylzdb\":\"预留字段b\",\"ylzdc\":\"预留字段c\",\"fxd\":\"风险点\",\"dtwzdd\":\"地图位置定点\",\"zrdw\":\"责任单位\",\"fxdj\":\"风险等级\",\"fxfl\":\"风险分类\",\"xzrs\":\"限制人数123\",\"fxms\":\"风险描述\",\"gkcs\":\"修改12\",\"zyfzr\":\"主要负责人\",\"fgfzr\":\"分管负责人\",\"fxlx\":\"类型(年度风险,专项风险)\",\"xgrq\":\"1970-01-19\",\"csbh\":\"3方法\",\"ysid\":1}]")
	})
	@PostMapping("/updateBatchById")
	public Object updateBatchById(String params) {
		if(log.isDebugEnabled())log.debug("updateById参数:\n{}",JSON.toJSONString(params));
		@SuppressWarnings("unchecked")
		Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		List<T> entities = JSON.parseArray(params, entityClass);
		Object result = service.updateBatchById(entities);
		return new ResultObject(ResultObject.SUCCESS,"1007","接口调用成功",result);
	}
	
	/*
	 * 删除
	 */
	@ApiOperation(value = "根据id删除数据",notes="删除( 注意: ajax 设置 traditional: true  防止深度序列化以方便后端接收数组)",response=ResultObject.class)
	@ApiImplicitParams
	({
		@ApiImplicitParam(value="需要删除的数据id字符串 多个以逗号隔开   [1,2].join(',') -> '1,2'    单条删除   [1].join(',')->'1'",dataType="String",
				name="params",paramType="query",required=true,defaultValue="1")
	})
	@PostMapping("/removeByIds")
	//ajax 设置 traditional: true  防止深度序列化以方便后端接收数组
	public Object removeByIds(String params) {

		if(log.isDebugEnabled())log.debug("removeByIds参数:\n{}",params);
		
		Object result = service.removeByIds(Arrays.asList(params.split(",")));
//		String ids = params.get("ids").toString();
//		Object result = service.removeByIds(JSON.parseArray(ids).toJavaList(ArrayList.class));
		return new ResultObject(ResultObject.SUCCESS,"1005","接口调用成功",result);
	}
	
	/*
	 *根据传入字段进行count统计(针对非数字类型字段)
	 */
	@ApiOperation(value = "单独统计",response=ResultObject.class)
	@ApiImplicitParams
	({
		//分组统计方式(年,月)
		@ApiImplicitParam(value="分组方式(年,年月) 默认使用年月",name="groupWay",dataType="String"
				,paramType="query",required=false,defaultValue="年月")
		//时间过滤 		年:2019		 年月:2019-3 
		,@ApiImplicitParam(value="时间过滤 		年:2019		 年月:2019-3",name="havingValue",dataType="String"
		,paramType="query",required=false,defaultValue="2019")
	})
	@PostMapping("/countBy")
	public Object countBy(@ApiParam(hidden=true)@RequestParam Map<String,Object> params
			, @ApiParam(hidden=true) HttpServletRequest request
			) {

		if(log.isDebugEnabled())log.debug("countBy参数:\n{}",params);
		HttpSession session = request.getSession();
		Object result = null;
		@SuppressWarnings("unchecked")
		Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		
		//用户区域部门列表sql ('数据所属煤矿','所属煤矿')
		String deptsStr = (String) session.getAttribute(ESessionKey.DeptsIdStr.key);
		
		//培训管理统计
		if(PxglSjlb.class.equals(entityClass)) {
			result = myService.countByPxglSjlb(params,deptsStr);
		}
		//生产管理 实际产量 按选中的年 各月分组统计
		if(ScglSjcl.class.equals(entityClass)) {
			result = myService.countByScglSjcl(params,deptsStr);
		}
		
		

		return new ResultObject(ResultObject.SUCCESS,"1005","接口调用成功",result);
	}
	
	
}