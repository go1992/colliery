package com.yw.colliery.service.impl;

import com.yw.colliery.entity.DdxxScsg;
import com.yw.colliery.mapper.DdxxScsgMapper;
import com.yw.colliery.service.IDdxxScsgService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 调度信息管理-生产安全事故处理追踪 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-03-02
 */
@Service
public class DdxxScsgServiceImpl extends ServiceImpl<DdxxScsgMapper, DdxxScsg> implements IDdxxScsgService {

	public IPage<Map<String,Object>> myCount(IPage<Map<String,Object>> page,@Param(Constants.WRAPPER) Wrapper<DdxxScsg> wrapper){
		return baseMapper.myCount(page, wrapper);
	}
	
}
