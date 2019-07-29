package com.yw.colliery.mapper.product;


import com.yw.colliery.entity.productmanager.SafetyInfoEntity;

import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface SafetyInfoMapper {

    /**
     * 插入安全信息数据
     * @param safetyInfoEntity
     * @return
     */
    Integer insertSafetyInfo(SafetyInfoEntity safetyInfoEntity);


    /**
     * 更新安全信息信息
     * @param safetyInfoEntity
     * @return
     *
     */
    Integer updateSafetyInfo(SafetyInfoEntity safetyInfoEntity);

    /**
     * 条件查询
     * @param safetyInfoEntity
     * @return
     */
    List<SafetyInfoEntity> selectBySafetyInfo(SafetyInfoEntity safetyInfoEntity);

    /**
     * 删除安全信息数据
     * @param idList
     * @return
     */
    Integer deleteById(List<String> idList);
}
