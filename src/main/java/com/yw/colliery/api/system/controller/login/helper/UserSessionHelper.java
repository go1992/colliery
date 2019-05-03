package com.yw.colliery.api.system.controller.login.helper;

import com.yw.colliery.api.base.ESessionKey;
import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.constans.LoginConstant;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.sdk.utils.SpringSessionUtils;
import com.yw.colliery.service.depart.DepartmentService;
import com.yw.colliery.service.user.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: renzhiqiang
 * @Description: 用户存储session过滤器
 * @Date: 2019/5/2
 */
@Component
public class UserSessionHelper {
    @Autowired
    private UserRelationService userRelationService;
    @Autowired
    private DepartmentService departmentService;

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
     * @param userName
     */
    public void updateUserRelationInfo(String userName) {
        UserRelationEntity user = LoginSessionUtils.getUser();
        if (user != null) {
            handleUser(userName);
        }
    }

    private void handleUser(String userName) {
        UserRelationEntity userRelation = userRelationService.selectByUserName(userName);
        if (userRelation != null) {
            SpringSessionUtils.setSession(LoginConstant.Session.WEBAPI_SESSION_USER, userRelation);
            DepartmentEntity depart = departmentService.selectById(userRelation.getSafetyUser().getDepartId());
            if (depart != null) {
                SpringSessionUtils.setSession(ESessionKey.DeptsIds.key, depart.getCoalMine());
                StringBuffer sbf = new StringBuffer("(");
                sbf.append(depart.getId());
                sbf.append(")");
                SpringSessionUtils.setSession(ESessionKey.DeptsIdStr.key, sbf.toString());
            }
        }
    }
}
