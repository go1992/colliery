package com.yw.colliery.service.business;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.colliery.entity.SbglSblb;

/**
 * <p>
 * 设备管理-设备列表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
public interface ISbglSblbService extends IService<SbglSblb> {
	List<Map<String,Object>> myCount(@Param(Constants.WRAPPER) Wrapper<SbglSblb> wrapper);
}
