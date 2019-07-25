package com.yw.colliery.service.business.impl;

import com.yw.colliery.entity.SecurityRiskEntity;
import com.yw.colliery.mapper.AqfxNdfxMapper;
import com.yw.colliery.sdk.request.YearUnsafeRequest;
import com.yw.colliery.sdk.response.vo.UnsafeLevelVo;
import com.yw.colliery.sdk.response.vo.UnsafeTypeVo;
import com.yw.colliery.sdk.utils.DateUtils;
import com.yw.colliery.service.business.IAqfxNdfxService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 安全风险管控-年度风险辨识 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@Service
public class AqfxNdfxServiceImpl extends ServiceImpl<AqfxNdfxMapper, SecurityRiskEntity> implements IAqfxNdfxService {
    @Autowired
    private AqfxNdfxMapper aqfxNdfxMapper;

    @Override
    public List<UnsafeTypeVo> statisUnsafeTypes(YearUnsafeRequest request) {
        List<SecurityRiskEntity> securityRiskEntities = aqfxNdfxMapper.selectByParams(request.getDatetype(), request.getFxfl(), request.getFxdj());

        if (CollectionUtils.isEmpty(securityRiskEntities)) {
            return null;
        }

        List<UnsafeTypeVo> unsafeTypeVos = new ArrayList<>();

        Map<String, List<SecurityRiskEntity>> unsafeTypeMap = securityRiskEntities.stream().filter(item -> item.getRiskSort() != null).collect(Collectors.groupingBy(SecurityRiskEntity::getRiskSort));
        for (Map.Entry<String, List<SecurityRiskEntity>> entry : unsafeTypeMap.entrySet()) {
            String fxfl = entry.getKey();
            List<SecurityRiskEntity> unsafeTypes = entry.getValue();
            UnsafeTypeVo unsafeTypeVo = new UnsafeTypeVo();
            unsafeTypeVo.setFxfl(fxfl);
            List<UnsafeLevelVo> levelVos = new ArrayList<>();
            Map<String, List<SecurityRiskEntity>> unsafeLevelMap = unsafeTypes.stream().filter(item -> item.getRiskLevel() != null).collect(Collectors.groupingBy(SecurityRiskEntity::getRiskLevel));
            for (Map.Entry<String, List<SecurityRiskEntity>> innerEntry : unsafeLevelMap.entrySet()) {
                String fxdj = innerEntry.getKey();
                int count = innerEntry.getValue().size();
                UnsafeLevelVo unsafeLevelVo = new UnsafeLevelVo();
                unsafeLevelVo.setFxdj(fxdj);
                unsafeLevelVo.setCount(count);
                levelVos.add(unsafeLevelVo);
            }
            unsafeTypeVo.setLevelVos(levelVos);
            unsafeTypeVos.add(unsafeTypeVo);

        }
        return unsafeTypeVos;
    }

    @Override
    public List<UnsafeLevelVo> statisUnsafeLevel(YearUnsafeRequest request) {
        List<SecurityRiskEntity> securityRiskEntities = aqfxNdfxMapper.selectByParams(request.getDatetype(), request.getFxfl(), request.getFxdj());

        if (CollectionUtils.isEmpty(securityRiskEntities)) {
            return null;
        }

        List<UnsafeLevelVo> levelVos = new ArrayList<>();

        Map<String, List<SecurityRiskEntity>> unsafeLevelMap = securityRiskEntities.stream().filter(item -> item.getRiskLevel() != null).collect(Collectors.groupingBy(SecurityRiskEntity::getRiskLevel));
        for (Map.Entry<String, List<SecurityRiskEntity>> entry : unsafeLevelMap.entrySet()) {
            String fxdj = entry.getKey();
            int count = entry.getValue().size();
            UnsafeLevelVo unsafeLevelVo = new UnsafeLevelVo();
            unsafeLevelVo.setFxdj(fxdj);
            unsafeLevelVo.setCount(count);
            levelVos.add(unsafeLevelVo);
        }

        return levelVos;
    }


    private Date calStartTime(String queryTime) {
        Date startTime = null;
        try {
            if (queryTime.indexOf("-") == 3) {
                //包含月份,计算月开始时间
                startTime = DateUtils.getMonthStartTime(queryTime);
            } else {
                startTime = DateUtils.getYearStartTime(queryTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return startTime;
    }

    private Date calEndTime(String queryTime) {
        Date startTime = null;
        try {
            if (queryTime.indexOf("-") == 3) {
                //包含月份,计算月开始时间
                startTime = DateUtils.getMonthEndTime(queryTime);
            } else {
                startTime = DateUtils.getYearEndTime(queryTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return startTime;
    }

}
