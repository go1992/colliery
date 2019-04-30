package com.yw.colliery.service.depart.impl;

import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.mapper.depart.DepartMapper;
import com.yw.colliery.service.depart.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 部门实现类
 * @Date 2019-04-30
 **/
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartMapper departMapper;

    @Override
    public int addDepart(DepartmentEntity entity) {
        return departMapper.addDepart(entity);
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
        return departMapper.selectById(departId);
    }

    @Override
    public List<DepartmentEntity> selectAll() {
        return departMapper.selectAll();
    }
}
