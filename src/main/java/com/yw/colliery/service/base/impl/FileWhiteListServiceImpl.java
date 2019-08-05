package com.yw.colliery.service.base.impl;

import com.github.pagehelper.PageHelper;
import com.yw.colliery.entity.base.BaseInfoConfigEntity;
import com.yw.colliery.entity.file.FileWhiteListEntity;
import com.yw.colliery.mapper.file.FileWhiteListMapper;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.base.FileWhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xuzhou-013
 * @Date: 2019/7/31 13:52
 * @Description: 文件白名单管理
 */
@Service
public class FileWhiteListServiceImpl implements FileWhiteListService {

    @Autowired
    private FileWhiteListMapper fileWhiteListMapper;
    @Override
    public Integer saveFileWhiteList(List<FileWhiteListEntity> entities) {
        return fileWhiteListMapper.insertFileWhiteList(entities);
    }

    @Override
    public Integer updateFileWhiteList(FileWhiteListEntity fileWhiteListEntity) {
        return fileWhiteListMapper.updateFileWhiteList(fileWhiteListEntity);
    }

    @Override
    public PageBean<FileWhiteListEntity> getAllFileWhiteList(FileWhiteListEntity entity, int pagNum, int pagSize) {
        PageHelper.startPage(pagNum, pagSize);
        List<FileWhiteListEntity> baseInfoConfigEntities = fileWhiteListMapper.selectFileWhiteList(entity);
        return new PageBean<>(baseInfoConfigEntities);
    }

    @Override
    public Integer deleteFileWhiteList(List<String> idList) {
        return fileWhiteListMapper.deleteById(idList);
    }
}
