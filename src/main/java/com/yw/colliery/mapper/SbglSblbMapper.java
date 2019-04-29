package com.yw.colliery.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yw.colliery.entity.SbglSblb;

/**
 * <p>
 * 设备管理-设备列表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
public interface SbglSblbMapper extends BaseMapper<SbglSblb> {

	List<Map<String,Object>> myCount(@Param(Constants.WRAPPER) Wrapper<SbglSblb> wrapper);
	
}
