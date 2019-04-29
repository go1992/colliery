package com.yw.colliery.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yw.colliery.entity.DdxxScsg;

/**
 * <p>
 * 调度信息管理-生产安全事故处理追踪 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-03-02
 */
public interface DdxxScsgMapper extends BaseMapper<DdxxScsg> {


	IPage<Map<String,Object>> myCount(IPage<Map<String, Object>> page, @Param(Constants.WRAPPER) Wrapper<DdxxScsg> wrapper);
	
}
