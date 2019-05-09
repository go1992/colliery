package com.yw.colliery.sdk.helper;

import com.yw.colliery.api.base.ESessionKey;
import com.yw.colliery.entity.XtgnYhlb;
import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.constans.LoginConstant;
import com.yw.colliery.sdk.message.event.UpdateSessionEvent;
import com.yw.colliery.sdk.message.listener.EventListener;
import com.yw.colliery.sdk.message.publisher.EventPublisher;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.sdk.utils.SpringSessionUtils;
import com.yw.colliery.service.depart.DepartEvent;
import com.yw.colliery.service.depart.DepartmentService;
import com.yw.colliery.service.role.RoleEvent;
import com.yw.colliery.service.user.UserRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @Author: renzhiqiang
 * @Description: 用户存储session过滤器
 * @Date: 2019/5/2
 */
@Component
public class UserSessionHelper implements EventListener<UpdateSessionEvent>, InitializingBean {
    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    private DepartmentService departmentService;
    private Logger logger = LoggerFactory.getLogger(UserSessionHelper.class);
    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public void afterPropertiesSet() throws Exception {
        eventPublisher.registListener(this);
    }

    /**
     * 保存用户关联信息到session
     */
    public void saveUserRelationInfo(String userName) {
        //先取，如果没有则查询用户信息，查到就放到session中
        UserRelationEntity userAuth = LoginSessionUtils.getUser();
        if (userAuth == null) {
            handleUser(userName);
        }
    }

    /**
     * 更新session中用户关联信息
     */
    public void updateUserRelationInfo() {
        UserRelationEntity user = LoginSessionUtils.getUser();
        if (user != null) {
            handleUser(user.getSafetyUser().getUsername());
        }
    }

    private void handleUser(String userName) {
        UserRelationEntity userRelation = userRelationService.selectByUserName(userName);
        if (userRelation != null) {
            SpringSessionUtils.setSession(LoginConstant.Session.WEBAPI_SESSION_USER, userRelation);
            DepartmentEntity depart = departmentService.selectById(userRelation.getSafetyUser().getDepartId());
            if (depart != null) {
                XtgnYhlb user = new XtgnYhlb();
                user.setId(Long.valueOf(userRelation.getSafetyUser().getId()));
                user.setSsmk(String.valueOf(depart.getId()));
                user.setYhmdd(userRelation.getSafetyUser().getUsername());
                user.setYhmm(userRelation.getSafetyUser().getPassword());
                user.setPcrq(userRelation.getSafetyUser().getCreateDate());
                user.setSsqymc(depart.getId());
                SpringSessionUtils.setSession(ESessionKey.User.key, user);
                SpringSessionUtils.setSession(ESessionKey.DeptsIds.key, Collections.singletonList(String.valueOf(depart.getId())));
                StringBuffer sbf = new StringBuffer("(");
                sbf.append(depart.getId());
                sbf.append(")");
                SpringSessionUtils.setSession(ESessionKey.DeptsIdStr.key, sbf.toString());
            }
        }
    }

    @Override
    public void listen(UpdateSessionEvent event) {
        if (event instanceof UpdateSessionEvent) {
            logger.info("收到消息类型：" + event.getClass().getName() + " 更新session用户信息!");
            updateUserRelationInfo();
        }
    }
}
