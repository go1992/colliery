package com.yw.colliery.service.depart.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.mapper.auth.AuthMapper;
import com.yw.colliery.mapper.depart.DepartMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.config.PageParam;
import com.yw.colliery.sdk.message.publisher.EventPublisher;
import com.yw.colliery.service.depart.DepartEvent;
import com.yw.colliery.service.depart.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author renzhiqiang
 * @Description 部门实现类
 * @Date 2019-04-30
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartMapper departMapper;
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public int addDepart(DepartmentEntity entity) {
        int result =  departMapper.addDepart(entity);
        if (result > 0) {
            eventPublisher.publish(new DepartEvent());
        }
        return result;
    }

    @Override
    public int updateDepart(DepartmentEntity entity) {
        return departMapper.updateDepart(entity);
    }

    @Override
    public int deleteDepart(Integer departId) {
        return departMapper.deleteDepart(departId);
    }

    @Override
    public DepartmentEntity selectById(Integer departId) {
        DepartmentEntity entity = departMapper.selectById(departId);
        if (entity != null) {
            List<AuthEntity> authList = Arrays.asList(entity.getAuthIds().split(","))
                    .stream()
                    .map(authId -> Integer.valueOf(authId))
                    .map(authId -> authMapper.selectById(authId))
                    .collect(Collectors.toList());
            entity.setAuthList(authList);
        }
        return entity;
    }

    @Override
    public List<DepartmentEntity> selectAll() {
        List<DepartmentEntity> departs =  departMapper.selectAll();
        for (DepartmentEntity entity : departs) {
            if (entity != null) {
                List<AuthEntity> authList = Arrays.asList(entity.getAuthIds().split(","))
                        .stream()
                        .map(authId -> Integer.valueOf(authId))
                        .map(authId -> authMapper.selectById(authId))
                        .collect(Collectors.toList());
                entity.setAuthList(authList);
            }
        }
        return departs;
    }

    @Override
    public PageBean<DepartmentEntity> selectByPage(PageParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize(), param.generateOderBy());
        List<DepartmentEntity> departs =  departMapper.selectAll();
        for (DepartmentEntity entity : departs) {
            if (entity != null) {
                List<AuthEntity> authList = Arrays.asList(entity.getAuthIds().split(","))
                        .stream()
                        .map(authId -> Integer.valueOf(authId))
                        .map(authId -> authMapper.selectById(authId))
                        .collect(Collectors.toList());
                entity.setAuthList(authList);
            }
        }
        return new PageBean(departs);
    }

    @Override
    public int deleteDepartByIds(List<Integer> departIds) {
        return departMapper.deleteDepartByIds(departIds);
    }
}
