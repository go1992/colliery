package com.yw.colliery.user;

import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @description: 用户查询接口
 * @author: xuzhou-lhq
 * @create: 2019-04-25 14:04
 **/
public interface CollierySafetyUserService extends UserDetailsService {

    CollierySafetyUserEntity selectByUserCode(String userCode);
}
