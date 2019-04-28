package com.example.demo.mapper;

import com.example.demo.entity.PxglSjlb;
import com.example.demo.entity.SbglSblb;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;

/**
 * <p>
 * 培训管理-数据列表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
public interface PxglSjlbMapper extends BaseMapper<PxglSjlb> {
	List<Map<String,Object>> myCount(@Param(Constants.WRAPPER) Wrapper<SbglSblb> wrapper);
}
