package com.yw.colliery.service.user;

import com.yw.colliery.entity.user.UserEntity;

/**
 * @Author renzhiqiang
 * @Description 用户接口
 * @Date 2019-05-01
 **/
public interface UserService {
    UserEntity selectByUserId(Integer userId);

    UserEntity selectByUserName(String userName);
}
