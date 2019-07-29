package com.yw.colliery.service.geological;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yw.colliery.entity.DcSwdzZkpc;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 地测信息管理-水文地质钻孔排查 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
public interface BoreholeService extends IService<DcSwdzZkpc> {

    List<DcSwdzZkpc> getAll(@Param(Constants.WRAPPER)Wrapper wrapper);

}
