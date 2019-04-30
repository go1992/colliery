package com.yw.colliery.service.user;

import com.yw.colliery.entity.user.UserAuthEntity;

/**
 * @Author renzhiqiang
 * @Description 用户权限接口
 * @Date 2019-04-30
 **/
public interface UserAuthService {
    UserAuthEntity selectByUserId(Integer userId);

    UserAuthEntity selectByUserName(String userName);
}
