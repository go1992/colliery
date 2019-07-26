package com.yw.colliery.mapper;

import com.yw.colliery.entity.AqfxNdfx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yw.colliery.entity.securityrisk.YearsSecurityRiskEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 安全风险管控-年度风险辨识 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
public interface AqfxNdfxMapper extends BaseMapper<AqfxNdfx> {

    /**
     * 条件查询
     * @param datetype
     * @param unSafeType
     * @param unSafeLevel
     * @return
     */
    List<AqfxNdfx> selectByParams(@Param("datetype")String datetype, @Param("unSafeType") String unSafeType,
                                 @Param("unSafeLevel")  String unSafeLevel);

    /**
     * 批量插入
     * @param riskEntities
     * @return
     */
    Integer insertByBatch(@Param("list")List<YearsSecurityRiskEntity> riskEntities);
}
