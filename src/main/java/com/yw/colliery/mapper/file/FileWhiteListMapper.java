package com.yw.colliery.mapper.file;


import com.yw.colliery.entity.file.FileWhiteListEntity;
import com.yw.colliery.entity.productmanager.EntryCollieryEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @author xuzhou
 * @date 2019/07/31
 */
@Mapper
public interface FileWhiteListMapper {

    /**
     * 插入文件白名单
     * @param entities
     * @return
     */
    Integer insertFileWhiteList(List<FileWhiteListEntity> entities);


    /**
     * 更新文件白名单
     * @param fileWhiteListEntity
     * @return
     *
     */
    Integer updateFileWhiteList(FileWhiteListEntity fileWhiteListEntity);

    /**
     * 条件查询
     * @param fileWhiteListEntity
     * @return
     */
    List<FileWhiteListEntity> selectFileWhiteList(FileWhiteListEntity fileWhiteListEntity);

    /**
     * 删除文件白名单
     * @param idList
     * @return
     */
    Integer deleteById(List<String> idList);
}
