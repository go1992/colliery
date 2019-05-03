package com.yw.colliery.service.role.impl;

import com.yw.colliery.entity.role.RoleEntity;
import com.yw.colliery.mapper.role.RoleMapper;
import com.yw.colliery.sdk.message.publisher.EventPublisher;
import com.yw.colliery.service.role.RoleEvent;
import com.yw.colliery.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 角色实现类
 * @Date 2019-04-30
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public int addRole(RoleEntity entity) {
        return roleMapper.addRole(entity);
    }

    @Override
    public int updateRole(RoleEntity entity) {
        int result = roleMapper.updateRole(entity);
        if (result > 0) {
            eventPublisher.publish(new RoleEvent());
        }
        return result;
    }

    @Override
    public int deleteRole(Integer roleId) {
        return roleMapper.deleteRole(roleId);
    }

    @Override
    public RoleEntity selectById(Integer roleId) {
        return roleMapper.selectById(roleId);
    }

    @Override
    public List<RoleEntity> selectAll() {
        return roleMapper.selectAll();
    }
}
