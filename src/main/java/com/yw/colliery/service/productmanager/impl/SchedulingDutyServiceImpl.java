package com.yw.colliery.service.productmanager.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.productmanager.SchedulingDutyEntity;
import com.yw.colliery.mapper.productmanager.SchedulingDutyMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.productmanager.BaseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingDutyServiceImpl implements BaseProductService<SchedulingDutyEntity> {

    @Autowired
    private SchedulingDutyMapper schedulingDutyMapper;

    @Override
    public Integer insert(SchedulingDutyEntity schedulingDutyEntity) {
        return schedulingDutyMapper.insertSchedulingDuty(schedulingDutyEntity);
    }

    @Override
    public PageBean<SchedulingDutyEntity> getByCondition(SchedulingDutyEntity schedulingDutyEntity, int pagNum, int pagSize) {
        PageHelper.startPage(pagNum, pagSize);
        List<SchedulingDutyEntity> unsafeInfoEntities = schedulingDutyMapper.selectBySchedulingDuty(schedulingDutyEntity);
        return new PageBean<>(unsafeInfoEntities);
    }

    @Override
    public Integer update(SchedulingDutyEntity schedulingDutyEntity) {
        return schedulingDutyMapper.updateSchedulingDuty(schedulingDutyEntity);
    }

    @Override
    public Integer delete(List<String> planIdList) {
        return schedulingDutyMapper.deleteById(planIdList);
    }
}
