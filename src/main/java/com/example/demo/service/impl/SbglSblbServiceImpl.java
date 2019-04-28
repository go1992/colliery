package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.SbglSblb;
import com.example.demo.mapper.SbglSblbMapper;
import com.example.demo.service.ISbglSblbService;

/**
 * <p>
 * 设备管理-设备列表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
@Service
public class SbglSblbServiceImpl extends ServiceImpl<SbglSblbMapper, SbglSblb> implements ISbglSblbService {

	public List<Map<String,Object>> myCount(@Param(Constants.WRAPPER) Wrapper<SbglSblb> wrapper){
		return baseMapper.myCount(wrapper);
	}
	
}
