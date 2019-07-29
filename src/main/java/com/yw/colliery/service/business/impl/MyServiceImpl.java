package com.yw.colliery.service.business.impl;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yw.colliery.sdk.utils.StringUtil;
import com.yw.colliery.mapper.MyMapper;
import com.yw.colliery.service.business.MyService;

@Service
public class MyServiceImpl implements MyService{
	
	@Autowired
	public MyMapper myMapper;
	
	public static final String DEPTS_SQL=" WHERE a.ssmk IN ";
	
	//培训管理统计
	@Override
	public Object countByPxglSjlb(Map<String,Object> params,String deptsStr) {
		//分组统计维度
		//默认采用年月格式分组
		String dateFormatStr = "%Y-%m";
		String groupWay = MapUtils.getString(params,"groupWay",null);
		boolean isYear = "年".equals(groupWay);
		boolean isYearAndMonth = "年月".equals(groupWay);
		dateFormatStr = StringUtil.checkNotNull(groupWay) ?
				isYear ? "%Y"//按年分组
				: isYearAndMonth ? "%Y-%m"//按年月分组
				: dateFormatStr//不是年 也不是年月
				: dateFormatStr;//分组格式为null
		
		//过滤参数 时间年月
		String havingValue = MapUtils.getString(params,"havingValue",null);
		boolean valueCheck =  StringUtil.checkNotNull(havingValue) && havingValue.matches("[0-9-]+");
		
		String countSql = ""+/**~!{*/""
			+ "SELECT "
			+ "\r\n	count(id) AS pxcs"
			+ "\r\n	,SUM(a.sjpxrs) AS sjpxrs"
			+ "\r\n	,SUM(a.jhpxrs) AS jhpxrs"
			+ "\r\n	,DATE_FORMAT( a.pxsj,'" +((dateFormatStr))+ "') AS pxsjstr"
			+ "\r\nFROM"
			+ "\r\n pxgl_sjlb a"
			+ "\r\n" +((DEPTS_SQL))+ "" +((deptsStr))+ ""
			+ "\r\nGROUP BY"
			+ "\r\n pxsjstr"
			+ "\r\n " +((valueCheck ? "HAVING pxsjstr='"+havingValue+"'": ""))+ ""
		+ "\r\n"/**}*/;
		return myMapper.getBySql(countSql);
	}

	//生产管理-实际产量 按月统计
	@Override
	public Object countByScglSjcl(Map<String, Object> params,String deptsStr) {
		
		//过滤参数 时间年月
		String havingValue = MapUtils.getString(params,"havingValue",null);
		boolean valueCheck =  StringUtil.checkNotNull(havingValue) && havingValue.matches("[0-9-]+");
		
		String countSql = ""+/**~!{*/""
			+ "SELECT"
			+ "\r\n	sum(a.drcl) AS shuliang,"
			+ "\r\n	DATE_FORMAT(a.pcrq,'%Y-%m') AS pcrqstr"
			+ "\r\nFROM"
			+ "\r\n	scgl_sjcl a"
			+ "\r\n" +((DEPTS_SQL))+ "" +((deptsStr))+ ""
			+ "\r\nGROUP BY"
			+ "\r\n	pcrqstr"
			+ "\r\n" +((valueCheck ? "HAVING pcrqstr LIKE '%"+havingValue+"%'": ""))+ ""
			+ "\r\nORDER BY "
			+ "\r\n	a.pcrq"
			+ "\r\n"
		+ "\r\n"/**}*/;
		return myMapper.getBySql(countSql);
	}
	

}



















































