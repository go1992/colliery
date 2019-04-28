package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.DcSwdzZkpc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 地测信息管理-水文地质钻孔排查 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
public interface DcSwdzZkpcMapper extends BaseMapper<DcSwdzZkpc> {

    List<DcSwdzZkpc> getAll(@Param(Constants.WRAPPER) Wrapper wrapper);

}
