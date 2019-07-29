package com.yw.colliery.mapper.product;


import com.yw.colliery.entity.productmanager.KeyProjectEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KeyProjectMapper {

    /**
     * 插入安全信息数据
     * @param keyProjectEntity
     * @return
     */
    Integer insertKeyProject(KeyProjectEntity keyProjectEntity);


    /**
     * 更新安全信息信息
     * @param keyProjectEntity
     * @return
     *
     */
    Integer updateKeyProject(KeyProjectEntity keyProjectEntity);

    /**
     * 条件查询
     * @param keyProjectEntity
     * @return
     */
    List<KeyProjectEntity> selectByKeyProject(KeyProjectEntity keyProjectEntity);

    /**
     * 删除安全信息数据
     * @param idList
     * @return
     */
    Integer deleteById(List<String> idList);
}
