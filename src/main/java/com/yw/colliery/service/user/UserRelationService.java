package com.yw.colliery.service.user;

import com.yw.colliery.entity.user.UserRelationEntity;

/**
 * @Author renzhiqiang
 * @Description 用户权限接口
 * @Date 2019-04-30
 **/
public interface UserRelationService {
    UserRelationEntity selectByUserId(Integer userId);

    UserRelationEntity selectByUserName(String userName);
}
