package com.yw.colliery.service.unsafe;


import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.entity.unsafe.UnsafePlanEntity;

import java.util.List;

public interface UnsafePlanService {

    /**
     * 隐患排查计划数据持久化
     * @param unsafePlanEntity
     * @return
     */
    Integer unsafePlanInsert(UnsafePlanEntity unsafePlanEntity);

    /**
     * 查询隐患排查计划
     * @param
     * @return
     */
    List<UnsafeInfoEntity> getUnsafePlanInfo(UnsafePlanEntity unsafePlanEntity);


    /**
     * 更新隐患数据
     * @param unsafePlanEntity
     * @return
     */
    Integer upateUnsafePlanInfo(UnsafePlanEntity unsafePlanEntity);

}
