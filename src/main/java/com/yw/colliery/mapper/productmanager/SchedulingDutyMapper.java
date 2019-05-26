package com.yw.colliery.mapper.productmanager;


import com.yw.colliery.entity.productmanager.SchedulingDutyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchedulingDutyMapper {

    /**
     * 插入调度员入井数据
     * @param schedulingDutyEntity
     * @return
     */
    Integer insertSchedulingDuty(SchedulingDutyEntity schedulingDutyEntity);


    /**
     * 更新调度员入井数据
     * @param schedulingDutyEntity
     * @return
     *
     */
    Integer updateSchedulingDuty(SchedulingDutyEntity schedulingDutyEntity);

    /**
     * 条件查询
     * @param schedulingDutyEntity
     * @return
     */
    List<SchedulingDutyEntity> selectBySchedulingDuty(SchedulingDutyEntity schedulingDutyEntity);

    /**
     * 删除调度员入井数据
     * @param idList
     * @return
     */
    Integer deleteById(List<String> idList);
}
