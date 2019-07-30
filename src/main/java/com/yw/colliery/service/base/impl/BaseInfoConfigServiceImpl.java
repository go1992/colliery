package com.yw.colliery.service.base.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.base.BaseInfoConfigEntity;
import com.yw.colliery.mapper.base.BaseInfoConfigMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.base.BaseInfoConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xuzhou
 * @Date: 2019/5/16 09:31
 * @Description: 基础信息配置持久化实现类
 */
@Service
public class BaseInfoConfigServiceImpl implements BaseInfoConfigService {

    @Autowired
    private BaseInfoConfigMapper baseInfoConfigMapper;
    @Override
    public Integer saveBaseConfig(List<BaseInfoConfigEntity> baseInfoConfigEntity) {
        return baseInfoConfigMapper.addConfig(baseInfoConfigEntity);
    }

    @Override
    public Integer updateBaseConfig(BaseInfoConfigEntity baseInfoConfigEntity) {
        return baseInfoConfigMapper.updateConfig(baseInfoConfigEntity);
    }

    @Override
    public PageBean<BaseInfoConfigEntity> getAllConfigByCondition(BaseInfoConfigEntity baseInfoConfigEntity, int pagNum, int pagSize) {
        PageHelper.startPage(pagNum, pagSize);
        List<BaseInfoConfigEntity> baseInfoConfigEntities = baseInfoConfigMapper.selectAll(baseInfoConfigEntity);
        return new PageBean<>(baseInfoConfigEntities);
    }

    @Override
    public Integer deleteConfig(List<String> idList) {
        return baseInfoConfigMapper.deleteConfigByIds(idList);
    }
}
