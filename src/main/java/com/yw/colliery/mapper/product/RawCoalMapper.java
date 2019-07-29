package com.yw.colliery.mapper.product;



import com.yw.colliery.entity.productmanager.RawCoalEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RawCoalMapper {

    /**
     * 插入安全信息数据
     * @param rawCoalEntity
     * @return
     */
    Integer insertRawCoal(RawCoalEntity rawCoalEntity);


    /**
     * 更新安全信息信息
     * @param rawCoalEntity
     * @return
     *
     */
    Integer updateRawCoal(RawCoalEntity rawCoalEntity);

    /**
     * 条件查询
     * @param rawCoalEntity
     * @return
     */
    List<RawCoalEntity> selectByRawCoal(RawCoalEntity rawCoalEntity);

    /**
     * 删除安全信息数据
     * @param idList
     * @return
     */
    Integer deleteById(List<String> idList);
}
