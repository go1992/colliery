package com.yw.colliery.mapper.productmanager;


import com.yw.colliery.entity.productmanager.EntryCollieryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EntryCollieryMapper {

    /**
     * 插入调度员入井数据
     * @param entryCollieryEntity
     * @return
     */
    Integer insertEntryColliery(EntryCollieryEntity entryCollieryEntity);


    /**
     * 更新调度员入井数据
     * @param entryCollieryEntity
     * @return
     *
     */
    Integer updateEntryColliery(EntryCollieryEntity entryCollieryEntity);

    /**
     * 条件查询
     * @param entryCollieryEntity
     * @return
     */
    List<EntryCollieryEntity> selectByEntryColliery(EntryCollieryEntity entryCollieryEntity);

    /**
     * 删除调度员入井数据
     * @param idList
     * @return
     */
    Integer deleteById(List<String> idList);
}
