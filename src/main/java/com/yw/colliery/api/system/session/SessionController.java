package com.yw.colliery.api.system.session;

import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.sdk.utils.SpringSessionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: renzhiqiang
 * @Description: session接口
 * @Date: 2019/5/8
 */
@RestController
@RequestMapping("/apiv1/session")
public class SessionController {

    @GetMapping("/get")
    public UserRelationEntity getUserFromSession() {
        return LoginSessionUtils.getUser();
    }

    @GetMapping("/destroy")
    public void destroySession() {
        SpringSessionUtils.clearSession();
    }
}
