package com.yw.colliery.service.base;

import com.yw.colliery.entity.base.BaseInfoConfigEntity;
import com.yw.colliery.sdk.config.PageBean;

import java.util.List;

/**
 * @Author: xuzhou
 * @Date: 2019/5/15 15:43
 * @Description: 基础信息配置持久化service
 */
public interface BaseInfoConfigService {

    /**
     * 保存配置数据
     * @param baseInfoConfigEntity
     * @return
     */
    Integer saveBaseConfig(List<BaseInfoConfigEntity> baseInfoConfigEntity);

    /**
     * 更新配置信息
     * @param baseInfoConfigEntity
     * @return
     */
    Integer updateBaseConfig(BaseInfoConfigEntity baseInfoConfigEntity);


    /**
     * 根据条件查询所有的配置信息
     * @param baseInfoConfigEntity
     * @return
     */
    PageBean<BaseInfoConfigEntity> getAllConfigByCondition(BaseInfoConfigEntity baseInfoConfigEntity, int pagNum, int pagSize);

    /**
     * 删除配置信息
     * @param idList
     * @return
     */
    Integer deleteConfig(List<String> idList);
}
