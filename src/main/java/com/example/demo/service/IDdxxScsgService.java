package com.example.demo.service;

import com.example.demo.entity.DdxxScsg;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 调度信息管理-生产安全事故处理追踪 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-03-02
 */
public interface IDdxxScsgService extends IService<DdxxScsg> {

	IPage<Map<String,Object>> myCount(IPage<Map<String, Object>> page, @Param(Constants.WRAPPER) Wrapper<DdxxScsg> wrapper);
	
}
