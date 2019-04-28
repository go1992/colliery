package com.example.demo.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MyMapper {

	List<Map<String,Object>> getBySql(@Param("doSql") String doSql);
	
}
