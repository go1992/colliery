package com.yw.colliery.mapper.unsafe;


import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.entity.unsafe.UnsafePlanEntity;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UnsafePlanMapper {

    /**
     * 插入隐患排查计划
     *
     * @param unsafePlanEntity
     * @return
     */
    Integer insertUnsafePlan(UnsafePlanEntity unsafePlanEntity);


    /**
     * 更新隐患排查计划
     *
     * @param unsafePlanEntity
     * @return
     */
    Integer updateUnsafePlan(UnsafePlanEntity unsafePlanEntity);

    /**
     * 查询隐患排查计划
     *
     * @return
     */
    List<UnsafeInfoEntity> selectByUnsafePlanInfo(UnsafePlanEntity unsafePlanEntity);

    /**
     * 删除隐患计划数据
     * @param planIdList
     * @return
     */
    Integer deleteById(List<String> planIdList);
}

