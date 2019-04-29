package com.yw.colliery.service.impl;

import com.yw.colliery.entity.PxglSjlb;
import com.yw.colliery.entity.SbglSblb;
import com.yw.colliery.mapper.PxglSjlbMapper;
import com.yw.colliery.service.IPxglSjlbService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 培训管理-数据列表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
@Service
public class PxglSjlbServiceImpl extends ServiceImpl<PxglSjlbMapper, PxglSjlb> implements IPxglSjlbService {
	public List<Map<String,Object>> myCount(@Param(Constants.WRAPPER) Wrapper<SbglSblb> wrapper){
		return baseMapper.myCount(wrapper);
	}
}
