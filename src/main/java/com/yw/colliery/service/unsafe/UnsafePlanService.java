package com.yw.colliery.service.unsafe;


import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.entity.unsafe.UnsafePlanEntity;
import com.yw.colliery.sdk.config.PageBean;

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
    PageBean getUnsafePlanInfo(UnsafePlanEntity unsafePlanEntity, int pagNum, int pagSize);


    /**
     * 更新隐患数据
     * @param unsafePlanEntity
     * @return
     */
    Integer upateUnsafePlanInfo(UnsafePlanEntity unsafePlanEntity);

    /**
     * 删除隐患计划数据
     * @param planIdList
     * @return
     */
    Integer deleteUnsafePlanInfo(List<String> planIdList);

    /**
     * 获取在使用的计划
     * @param planIdList
     * @return
     */
    List<String> getAllUsedPlan(List<String> planIdList);

}
