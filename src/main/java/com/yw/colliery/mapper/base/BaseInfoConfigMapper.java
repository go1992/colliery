package com.yw.colliery.mapper.base;


import com.yw.colliery.entity.base.BaseInfoConfigEntity;
import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseInfoConfigMapper {

    /**
     * 插入配置数据
     * @param baseInfoConfigEntity
     * @return
     */
    Integer addConfig(BaseInfoConfigEntity baseInfoConfigEntity);


    /**
     * 更新配置信息
     * @param baseInfoConfigEntity
     * @return
     */
    Integer updateConfig(BaseInfoConfigEntity baseInfoConfigEntity);


    /**
     * 条件查询
     * @param baseInfoConfigEntity
     * @return
     */
    List<BaseInfoConfigEntity> selectAll(BaseInfoConfigEntity baseInfoConfigEntity);

    /**
     * 删除隐患数据
     * @param ids
     * @return
     */
    Integer deleteConfigByIds(List<String> ids);
}
