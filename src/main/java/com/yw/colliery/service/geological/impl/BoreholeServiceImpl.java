package com.yw.colliery.service.geological.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.yw.colliery.entity.DcSwdzZkpc;
import com.yw.colliery.mapper.DcSwdzZkpcMapper;
import com.yw.colliery.service.geological.BoreholeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 地测信息管理-水文地质钻孔排查 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@Service
public class BoreholeServiceImpl extends ServiceImpl<DcSwdzZkpcMapper, DcSwdzZkpc> implements BoreholeService {
    @Autowired
    DcSwdzZkpcMapper dcSwdzZkpcMapper;

    @Override
    public List<DcSwdzZkpc> getAll(Wrapper wrapper) {
        return dcSwdzZkpcMapper.getAll(wrapper);
    }
}
