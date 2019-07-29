package com.yw.colliery.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.productmanager.KeyProjectEntity;
import com.yw.colliery.mapper.product.KeyProjectMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.product.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyProjectServiceImpl implements BaseProductService<KeyProjectEntity> {

    @Autowired
    private KeyProjectMapper KeyProjectMapper;

    @Override
    public Integer insert(KeyProjectEntity keyProjectEntity) {
        return KeyProjectMapper.insertKeyProject(keyProjectEntity);
    }

    @Override
    public PageBean<KeyProjectEntity> getByCondition(KeyProjectEntity keyProjectEntity, int pagNum, int pagSize) {
        PageHelper.startPage(pagNum, pagSize);
        List<KeyProjectEntity> unsafeInfoEntities = KeyProjectMapper.selectByKeyProject(keyProjectEntity);
        return new PageBean<>(unsafeInfoEntities);
    }

    @Override
    public Integer update(KeyProjectEntity keyProjectEntity) {
        return KeyProjectMapper.updateKeyProject(keyProjectEntity);
    }

    @Override
    public Integer delete(List<String> planIdList) {
        return KeyProjectMapper.deleteById(planIdList);
    }
}
