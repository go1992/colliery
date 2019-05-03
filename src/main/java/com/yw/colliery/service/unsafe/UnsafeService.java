package com.yw.colliery.service.unsafe;


import com.yw.colliery.api.base.ResultObject;
import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;

import java.util.List;

public interface UnsafeService {

    /**
     * 隐患数据持久化
     * @param unsafeInfoEntity
     * @return
     */
    Integer unsafeInsert(UnsafeInfoEntity unsafeInfoEntity);

    /**
     * 获取所有的未处理的隐患信息
     * @param
     * @return
     */
    List<UnsafeInfoEntity> getAllUnsafeInfo();

    /**
     * 获取所有的未处理的隐患信息
     * @param
     * @return
     */
    List<UnsafeInfoEntity> getUnsafeInfoByDepartId(Integer departId);

    /**
     * 更新隐患数据
     * @param unsafeInfoEntity
     * @return
     */
    Integer upateUnsafeInfo(UnsafeInfoEntity unsafeInfoEntity);

    /**
     * 条件查询隐患数据
     * @param unsafeInfoEntity
     * @return
     */
    List<UnsafeInfoEntity> getUnsafeInfoByUnsafeInfoEntity(UnsafeInfoEntity unsafeInfoEntity);
}
