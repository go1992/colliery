package com.yw.colliery.service.unsafe.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.entity.unsafe.UnsafePlanEntity;
import com.yw.colliery.mapper.unsafe.UnsafeMapper;
import com.yw.colliery.mapper.unsafe.UnsafePlanMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.unsafe.UnsafePlanService;
import com.yw.colliery.service.unsafe.UnsafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnsafePlanServiceImpl implements UnsafePlanService{

    @Autowired
    private UnsafePlanMapper unsafePlanMapper;

    @Override
    public Integer unsafePlanInsert(UnsafePlanEntity unsafePlanEntity) {
        return unsafePlanMapper.insertUnsafePlan(unsafePlanEntity);
    }

    @Override
    public PageBean<UnsafeInfoEntity> getUnsafePlanInfo(UnsafePlanEntity unsafePlanEntity,int pagNum,int pagSize) {
        PageHelper.startPage(pagNum, pagSize);
        List<UnsafeInfoEntity> unsafeInfoEntities = unsafePlanMapper.selectByUnsafePlanInfo(unsafePlanEntity);
        return new PageBean<>(unsafeInfoEntities);
    }

    @Override
    public Integer upateUnsafePlanInfo(UnsafePlanEntity unsafePlanEntity) {
        return unsafePlanMapper.updateUnsafePlan(unsafePlanEntity);
    }

    @Override
    public Integer deleteUnsafePlanInfo(List<String> planIdList) {
        return unsafePlanMapper.deleteById(planIdList);
    }
}
