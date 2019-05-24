package com.yw.colliery;

import static com.yw.colliery.api.base.MyUtil.checkNotNull;

import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yw.colliery.api.base.ESessionKey;
import com.yw.colliery.api.base.MyUtil;
import com.yw.colliery.entity.AqfxCsxg;
import com.yw.colliery.entity.DcSwdzZkpc;
import com.yw.colliery.entity.DdxxScsg;
import com.yw.colliery.entity.WxyglSjlb;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;


public class TestBasic {
	@Data
	@AllArgsConstructor
	class Coo{
		private Long key;
		private String value;
	}
	
	@Test
	public void t19() {
		QueryWrapper<WxyglSjlb> qw = new QueryWrapper<WxyglSjlb>();
		qw.select("count(*) AS shuliang");
		qw.select("123");
		qw.groupBy("1234");
		qw.groupBy("98k");
		System.out.println(qw.getSqlSelect());
		System.out.println(qw.getSqlSegment());
//		qw.select(WxyglSjlb.class,i -> {
//			System.out.println(JSON.toJSONString(i));
//			System.out.println(i.getProperty());
//			return true;
//		} );
	}
	public void t18() {
		List<Coo> list = new ArrayList<Coo>();
		list.add(new Coo(1l,"value1"));
		list.add(new Coo(2l,"value2"));
		list.add(new Coo(3l,"value3"));
		list.add(new Coo(4l,"value4"));
		list.add(new Coo(5l,"value5"));
		List<String> list2 = null;
		StringBuffer sbf = new StringBuffer("(");
		list2 = list.stream().map((obj)->{
			String str = obj.key.toString();
			sbf.append("'");
			sbf.append(obj.key);
			sbf.append("',");
			return str;
		}).collect(Collectors.toList());
		sbf.append(")");
		sbf.replace(sbf.lastIndexOf(","), sbf.lastIndexOf(",")+1, "");
		System.out.println(JSON.toJSONString(list2));
		System.out.println(sbf.toString());
	}
	
	
	public void t17() {
		System.out.println(ESessionKey.User);
	}
	
	public void t16() {
		System.out.println(DigestUtils.md5DigestAsHex("admin".getBytes()));
	}
	
	class Aoo<T>{
		T obj;
		Aoo(){
			@SuppressWarnings("unchecked")
			Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			String classStr = entityClass.toString();
			String tableName = classStr.substring(classStr.lastIndexOf(".")+1,classStr.length());
			System.out.println(tableName);
		}
	}
	
	class Boo extends Aoo<AqfxCsxg>{
		Boo(){

		}
	}
	
	
	public void t15() {
		String str = "bbq";
		System.out.println(""+/**~!{*/""
			+ "" +((false?"123213":str))+ ""
			+ "\r\n" +(("$"))+ "{param}"
			+ "\r\n"
		+ "\r\n"/**}*/);
	}
	
	public void t14() {
		System.out.println("2019-'".matches("[0-9-]+"));
	}
	
	public void t13() {
		DateTime currentTime = new DateTime();
		System.out.println(DateTimeFormat.forPattern("yyyy-M").print(currentTime));
	}
	
	public void t12() {
		new Boo();
		
	}
	
	public void t11() {
		System.out.println(MyUtil.camelToUnderline("MyName"));
	}
	
	public void t10() throws JsonProcessingException {
		AqfxCsxg ac = new AqfxCsxg();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setSerializationInclusion(Include.NON_NULL);      
        String reqJson = mapper.writeValueAsString(ac);
        System.out.println(reqJson);
	}
	
	public void t9() {
		System.out.println("'abgVBBG'".matches("[a-zA-Z]+"));
	}
	
	public void t8() {
		System.out.println("" instanceof String);
	}
	
	public void t7() throws ParseException {
	}
	
	public void t6() {
		System.out.println(""+/**~!{*/"" +(("$"))+ "{123}"/**}*/);
	}
	
	public void t5() {
		System.err.println("单元测试n\t\t");
		QueryWrapper<DdxxScsg> qw = new QueryWrapper<DdxxScsg>();
		qw.select("id");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sgjb", "  1 ");
		qw.allEq(params,false);
		qw.groupBy("id");
		System.err.println("单元测试n\t\t"
			+"\n\t"+qw.getCustomSqlSegment()
			+"\n\t"+qw.getParamAlias()
			+"\n\t"+qw.getSqlSegment()
			+"\n\t"+qw.getSqlSelect()
			+"\n\t"+qw.getExpression()
			+"\n\t"+qw.getParamNameValuePairs()
			+"\n\t"+qw.getSqlSet()
		);
	}
	
	public void t4() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("", "");
		List<String> list = new ArrayList<String>();
//		list.add("");
//		System.out.println(list.size());
		System.out.println(checkNotNull("1",map,list));
	}
	
	public void t3() {
		System.out.println("".split(",").length);
	}
	
	public void t2() {
		System.out.println((System.currentTimeMillis()-(1000l*60l*60l*24l*365l*2l))+"  "+System.currentTimeMillis());
		System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
	}
	
	public void t1() {
		DcSwdzZkpc obj = new DcSwdzZkpc();
		obj.setId(12332123l);
//		obj.setZkwz(1232132);
		System.out.println(obj);
		System.out.println(JSON.toJSONString(obj));
	}
	
}
