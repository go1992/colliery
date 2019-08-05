package com.yw.colliery.service.base;

import com.yw.colliery.entity.base.BaseInfoConfigEntity;
import com.yw.colliery.entity.file.FileWhiteListEntity;
import com.yw.colliery.sdk.config.PageBean;

import java.util.List;

/**
 * @Author: xuzhou
 * @Date: 2019/5/15 15:43
 * @Description: 基础信息配置持久化service
 */
public interface FileWhiteListService {

    /**
     * 保存文件白名单
     * @param entities
     * @return
     */
    Integer saveFileWhiteList(List<FileWhiteListEntity> entities);

    /**
     * 更新更新文件白名单
     * @param fileWhiteListEntity
     * @return
     */
    Integer updateFileWhiteList(FileWhiteListEntity fileWhiteListEntity);


    /**
     * 根据条件查询所有文件白名单
     * @param entity
     * @return
     */
    PageBean<FileWhiteListEntity> getAllFileWhiteList(FileWhiteListEntity entity, int pagNum, int pagSize);

    /**
     * 删除白名单
     * @param idList
     * @return
     */
    Integer deleteFileWhiteList(List<String> idList);
}
