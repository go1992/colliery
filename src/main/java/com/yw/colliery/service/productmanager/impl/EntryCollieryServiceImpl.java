package com.yw.colliery.service.productmanager.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.productmanager.EntryCollieryEntity;
import com.yw.colliery.mapper.productmanager.EntryCollieryMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.productmanager.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryCollieryServiceImpl implements BaseProductService<EntryCollieryEntity> {

    @Autowired
    private EntryCollieryMapper entryCollieryMapper;

    @Override
    public Integer insert(EntryCollieryEntity entryCollieryEntity) {
        return entryCollieryMapper.insertEntryColliery(entryCollieryEntity);
    }

    @Override
    public PageBean<EntryCollieryEntity> getByCondition(EntryCollieryEntity entryCollieryEntity, int pagNum, int pagSize) {
        PageHelper.startPage(pagNum, pagSize);
        List<EntryCollieryEntity> unsafeInfoEntities = entryCollieryMapper.selectByEntryColliery(entryCollieryEntity);
        return new PageBean<>(unsafeInfoEntities);
    }

    @Override
    public Integer update(EntryCollieryEntity entryCollieryEntity) {
        return entryCollieryMapper.updateEntryColliery(entryCollieryEntity);
    }

    @Override
    public Integer delete(List<String> planIdList) {
        return entryCollieryMapper.deleteById(planIdList);
    }
}
