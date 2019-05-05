package com.yw.colliery.service.unsafe.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.mapper.unsafe.UnsafeMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.unsafe.UnsafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnsafeServiceImpl implements UnsafeService {

    @Autowired
    private UnsafeMapper unsafeMapper;


    @Override
    public Integer unsafeInsert(UnsafeInfoEntity unsafeInfoEntity) {
        return unsafeMapper.insertUnsafe(unsafeInfoEntity);
    }

    @Override
    public ArrayList<UnsafeInfoEntity> getAllUnsafeInfo() {
        return unsafeMapper.selectAllUnsafeInfo();
    }

    @Override
    public List<UnsafeInfoEntity> getUnsafeInfoByDepartId(Integer departId) {
        return unsafeMapper.selectUnsafeInfoByDepartList(departId);
    }

    @Override
    public Integer upateUnsafeInfo(UnsafeInfoEntity unsafeInfoEntity) {
        return unsafeMapper.updateUnsafeInfo(unsafeInfoEntity);
    }

    @Override
    public PageBean<UnsafeInfoEntity> getUnsafeInfoByUnsafeInfoEntity(UnsafeInfoEntity unsafeInfoEntity,int pagNum,int pagSize) {
        PageHelper.startPage(pagNum, pagSize);
        List<UnsafeInfoEntity> unsafeInfoEntities = unsafeMapper.selectByUnsafeInfo(unsafeInfoEntity);
        return new PageBean<>(unsafeInfoEntities);
    }

    @Override
    public Integer delete(List<String> idList) {
        return unsafeMapper.deleteById(idList);
    }
}
