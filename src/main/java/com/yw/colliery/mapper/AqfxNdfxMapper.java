package com.yw.colliery.mapper;

import com.yw.colliery.entity.SecurityRiskEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 安全风险管控-年度风险辨识 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
public interface AqfxNdfxMapper extends BaseMapper<SecurityRiskEntity> {

    List<SecurityRiskEntity> selectByParams(@Param("datetype")String datetype, @Param("unSafeType") String unSafeType,
                                            @Param("unSafeLevel")  String unSafeLevel);
}
