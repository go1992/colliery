package com.yw.colliery.mapper.product;


import com.yw.colliery.entity.productmanager.HandOverEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HandOverMapper {

    /**
     * 插入安全信息数据
     * @param handOverEntity
     * @return
     */
    Integer insertHandOver(HandOverEntity handOverEntity);


    /**
     * 更新安全信息信息
     * @param handOverEntity
     * @return
     *
     */
    Integer updateHandOver(HandOverEntity handOverEntity);

    /**
     * 条件查询
     * @param handOverEntity
     * @return
     */
    List<HandOverEntity> selectByHandOver(HandOverEntity handOverEntity);

    /**
     * 删除安全信息数据
     * @param idList
     * @return
     */
    Integer deleteById(List<String> idList);
}
