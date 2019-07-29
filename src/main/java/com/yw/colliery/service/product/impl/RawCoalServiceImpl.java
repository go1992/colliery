package com.yw.colliery.service.product.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.productmanager.RawCoalEntity;
import com.yw.colliery.mapper.product.RawCoalMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.product.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawCoalServiceImpl implements BaseProductService<RawCoalEntity> {

    @Autowired
    private RawCoalMapper RawCoalMapper;

    @Override
    public Integer insert(RawCoalEntity rawCoalEntity) {
        return RawCoalMapper.insertRawCoal(rawCoalEntity);
    }

    @Override
    public PageBean<RawCoalEntity> getByCondition(RawCoalEntity rawCoalEntity, int pagNum, int pagSize) {
        PageHelper.startPage(pagNum, pagSize);
        List<RawCoalEntity> unsafeInfoEntities = RawCoalMapper.selectByRawCoal(rawCoalEntity);
        return new PageBean<>(unsafeInfoEntities);
    }

    @Override
    public Integer update(RawCoalEntity rawCoalEntity) {
        return RawCoalMapper.updateRawCoal(rawCoalEntity);
    }

    @Override
    public Integer delete(List<String> planIdList) {
        return RawCoalMapper.deleteById(planIdList);
    }
}
