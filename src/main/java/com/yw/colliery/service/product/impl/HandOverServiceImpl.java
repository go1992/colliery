package com.yw.colliery.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.productmanager.HandOverEntity;
import com.yw.colliery.mapper.product.HandOverMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.product.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HandOverServiceImpl implements BaseProductService<HandOverEntity> {

    @Autowired
    private HandOverMapper HandOverMapper;

    @Override
    public Integer insert(HandOverEntity handOverEntity) {
        return HandOverMapper.insertHandOver(handOverEntity);
    }

    @Override
    public PageBean<HandOverEntity> getByCondition(HandOverEntity handOverEntity, int pagNum, int pagSize) {
        PageHelper.startPage(pagNum, pagSize);
        List<HandOverEntity> unsafeInfoEntities = HandOverMapper.selectByHandOver(handOverEntity);
        return new PageBean<>(unsafeInfoEntities);
    }

    @Override
    public Integer update(HandOverEntity handOverEntity) {
        return HandOverMapper.updateHandOver(handOverEntity);
    }

    @Override
    public Integer delete(List<String> planIdList) {
        return HandOverMapper.deleteById(planIdList);
    }
}
