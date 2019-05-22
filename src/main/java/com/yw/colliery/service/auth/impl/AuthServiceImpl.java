package com.yw.colliery.service.auth.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.mapper.auth.AuthMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.config.BaseParam;
import com.yw.colliery.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 权限实现类
 * @Date 2019-04-30
 **/
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    @Override
    public int addAuth(AuthEntity entity) {
        return authMapper.addAuth(entity);
    }

    @Override
    public int updateAuth(AuthEntity entity) {
        return authMapper.updateAuth(entity);
    }

    @Override
    public int deleteAuth(Integer authId) {
        return authMapper.deleteAuth(authId);
    }

    @Override
    public AuthEntity selectById(Integer authId) {
        return authMapper.selectById(authId);
    }

    @Override
    public List<AuthEntity> selectByIds(List<Integer> ids) {
        return authMapper.selectByIds(ids);
    }

    @Override
    public List<AuthEntity> selectByLevelAndIds(Integer level, List<Integer> ids) {
        return authMapper.selectByLevelAndIds(level, ids);
    }

    @Override
    public List<AuthEntity> selectAll() {
        return authMapper.selectAll();
    }

    @Override
    public PageBean<AuthEntity> selectByPage(BaseParam param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize(), param.generateOderBy());
        return new PageBean<>(authMapper.selectAll());
    }

    @Override
    public int deleteAuthByIds(List<Integer> authIds) {
        return authMapper.deleteAuthByIds(authIds);
    }
}
