package com.yw.colliery.mapper.user;


import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
* @description: 验证成功处理
* @author xuzhou-lhq
* @date 2019/4/25
*/
@Mapper
public interface CollierySafetyUserMapper {

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入用户信息
     * @param record
     * @return
     */
    int insert(CollierySafetyUserEntity record);

    /**
     * 插入用户信息
     * @param record
     * @return
     */
    int insertSelective(CollierySafetyUserEntity record);

    int updateByPrimaryKeySelective(CollierySafetyUserEntity record);

    int updateByPrimaryKey(CollierySafetyUserEntity record);

    /**
     * 根据用户名查询
     * * @param userCode
     * @return
     */
    CollierySafetyUserEntity selectByUserName(String userName);

    /**
     * 查询所有用户
     * @return
     */
    List<CollierySafetyUserEntity> selectAllUser();

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    CollierySafetyUserEntity selectByUserId(Integer userId);
}