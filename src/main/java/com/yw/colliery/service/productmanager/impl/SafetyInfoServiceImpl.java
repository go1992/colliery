package com.yw.colliery.service.productmanager.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.productmanager.SafetyInfoEntity;
import com.yw.colliery.mapper.productmanager.SafetyInfoMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.productmanager.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SafetyInfoServiceImpl implements BaseProductService<SafetyInfoEntity> {

    @Autowired
    private SafetyInfoMapper safetyInfoMapper;

    @Override
    public Integer insert(SafetyInfoEntity safetyInfoEntity) {
        return safetyInfoMapper.insertSafetyInfo(safetyInfoEntity);
    }

    @Override
    public PageBean<SafetyInfoEntity> getByCondition(SafetyInfoEntity safetyInfoEntity, int pagNum, int pagSize) {
        PageHelper.startPage(pagNum, pagSize);
        List<SafetyInfoEntity> unsafeInfoEntities = safetyInfoMapper.selectBySafetyInfo(safetyInfoEntity);
        return new PageBean<>(unsafeInfoEntities);
    }

    @Override
    public Integer update(SafetyInfoEntity safetyInfoEntity) {
        return safetyInfoMapper.updateSafetyInfo(safetyInfoEntity);
    }

    @Override
    public Integer delete(List<String> planIdList) {
        return safetyInfoMapper.deleteById(planIdList);
    }
}
