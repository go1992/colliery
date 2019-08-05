package com.yw.colliery.service.user;

import com.yw.colliery.entity.user.UserRelationEntity;

/**
 * @Author renzhiqiang
 * @Description 用户权限接口
 * @Date 2019-04-30
 **/
public interface UserRelationService {

    /**
     * 主键查找用户
     * @param userId
     * @return
     */
    UserRelationEntity selectByUserId(Integer userId);

    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    UserRelationEntity selectByUserName(String userName);
}
