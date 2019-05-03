package com.yw.colliery.mapper.unsafe;


import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UnsafeMapper {

    /**
     * 插入隐患数据
     * @param unsafeInfoEntity
     * @return
     */
    Integer insertUnsafe(UnsafeInfoEntity unsafeInfoEntity);


    /**
     * 根据部门列表查询隐患信息
     * @param depart
     * @return
     */
    List<UnsafeInfoEntity> selectUnsafeInfoByDepartList(Integer depart);

    /**
     * 查询所有未处理或者超时的隐患信息
     * @return
     */
    ArrayList<UnsafeInfoEntity> selectAllUnsafeInfo();

    /**
     * 更新隐患信息
     * @param unsafeInfoEntity
     * @return
     */
    Integer updateUnsafeInfo(UnsafeInfoEntity unsafeInfoEntity);

    /**
     * 条件查询
     * @param unsafeInfoEntity
     * @return
     */
    List<UnsafeInfoEntity> selectByUnsafeInfo(UnsafeInfoEntity unsafeInfoEntity);
}
