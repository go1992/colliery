package com.yw.colliery.service.risk;

import com.yw.colliery.entity.securityrisk.AqfxNdfx;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.colliery.entity.securityrisk.YearsSecurityRiskEntity;
import com.yw.colliery.sdk.request.YearUnsafeRequest;
import com.yw.colliery.sdk.response.vo.UnsafeLevelVo;
import com.yw.colliery.sdk.response.vo.UnsafeTypeVo;

import java.util.List;

/**
 * <p>
 * 安全风险管控-年度风险辨识 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
public interface RiskService extends IService<AqfxNdfx> {


    List<UnsafeTypeVo> statisUnsafeTypes(YearUnsafeRequest request);

    List<UnsafeLevelVo> statisUnsafeLevel(YearUnsafeRequest request);

    Integer saveAll(List<YearsSecurityRiskEntity> riskEntities);
}
