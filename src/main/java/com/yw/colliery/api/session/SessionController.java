package com.yw.colliery.api.session;

import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.sdk.utils.SpringSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(SessionController.class);
    @GetMapping("/get")
    public UserRelationEntity getUserFromSession() {
        return LoginSessionUtils.getUser();
    }

    @GetMapping("/destroy")
    public void destroySession() {
        logger.info("销毁用户session!");
        SpringSessionUtils.clearSession();
    }
}
