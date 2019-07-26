package com.yw.colliery.service.business.impl;

import com.yw.colliery.entity.AqfxNdfx;
import com.yw.colliery.entity.securityrisk.YearsSecurityRiskEntity;
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

import java.text.SimpleDateFormat;
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
public class AqfxNdfxServiceImpl extends ServiceImpl<AqfxNdfxMapper, AqfxNdfx> implements IAqfxNdfxService {
    @Autowired
    private AqfxNdfxMapper aqfxNdfxMapper;

    @Override
    public List<UnsafeTypeVo> statisUnsafeTypes(YearUnsafeRequest request) {
        List<AqfxNdfx> aqfxNdfxes = aqfxNdfxMapper.selectByParams(request.getDatetype(), request.getFxfl(), request.getFxdj());

        if (CollectionUtils.isEmpty(aqfxNdfxes)) {
            return null;
        }

        List<UnsafeTypeVo> unsafeTypeVos = new ArrayList<>();

        Map<String, List<AqfxNdfx>> unsafeTypeMap = aqfxNdfxes.stream().filter(item -> item.getFxfl() != null).collect(Collectors.groupingBy(AqfxNdfx::getFxfl));
        for (Map.Entry<String, List<AqfxNdfx>> entry : unsafeTypeMap.entrySet()) {
            String fxfl = entry.getKey();
            List<AqfxNdfx> unsafeTypes = entry.getValue();
            UnsafeTypeVo unsafeTypeVo = new UnsafeTypeVo();
            unsafeTypeVo.setFxfl(fxfl);
            List<UnsafeLevelVo> levelVos = new ArrayList<>();
            Map<String, List<AqfxNdfx>> unsafeLevelMap = unsafeTypes.stream().filter(item -> item.getFxdj() != null).collect(Collectors.groupingBy(AqfxNdfx::getFxdj));
            for (Map.Entry<String, List<AqfxNdfx>> innerEntry : unsafeLevelMap.entrySet()) {
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
        List<AqfxNdfx> aqfxNdfxes = aqfxNdfxMapper.selectByParams(request.getDatetype(), request.getFxfl(), request.getFxdj());

        if (CollectionUtils.isEmpty(aqfxNdfxes)) {
            return null;
        }

        List<UnsafeLevelVo> levelVos = new ArrayList<>();

        Map<String, List<AqfxNdfx>> unsafeLevelMap = aqfxNdfxes.stream().filter(item -> item.getFxdj() != null).collect(Collectors.groupingBy(AqfxNdfx::getFxdj));
        for (Map.Entry<String, List<AqfxNdfx>> entry : unsafeLevelMap.entrySet()) {
            String fxdj = entry.getKey();
            int count = entry.getValue().size();
            UnsafeLevelVo unsafeLevelVo = new UnsafeLevelVo();
            unsafeLevelVo.setFxdj(fxdj);
            unsafeLevelVo.setCount(count);
            levelVos.add(unsafeLevelVo);
        }

        return levelVos;
    }

    @Override
    public Integer saveAll(List<YearsSecurityRiskEntity> riskEntities) {
        return aqfxNdfxMapper.insertByBatch(riskEntities);
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
