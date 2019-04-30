package com.yw.colliery.service.business;

import com.yw.colliery.entity.PxglSjlb;
import com.yw.colliery.entity.SbglSblb;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 培训管理-数据列表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
public interface IPxglSjlbService extends IService<PxglSjlb> {
	List<Map<String,Object>> myCount(@Param(Constants.WRAPPER) Wrapper<SbglSblb> wrapper);
}
