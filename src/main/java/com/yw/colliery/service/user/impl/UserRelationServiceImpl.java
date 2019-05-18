package com.yw.colliery.service.user.impl;

import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.entity.role.RoleEntity;
import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.service.auth.AuthService;
import com.yw.colliery.service.depart.DepartmentService;
import com.yw.colliery.service.role.RoleService;
import com.yw.colliery.service.user.CollierySafetyUserService;
import com.yw.colliery.service.user.UserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author renzhiqiang
 * @Description 用户权限实现类
 * @Date 2019-04-30
 **/
@Service
public class UserRelationServiceImpl implements UserRelationService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CollierySafetyUserService collierySafetyUserService;

    @Override
    public UserRelationEntity selectByUserId(Integer userId) {
        CollierySafetyUserEntity user = collierySafetyUserService.selectyUserId(userId);
        return handUser(user);
    }

    @Override
    public UserRelationEntity selectByUserName(String userName) {
        CollierySafetyUserEntity user = collierySafetyUserService.selectByUserName(userName);
        return handUser(user);
    }

    private UserRelationEntity handUser(CollierySafetyUserEntity user) {
        if (user != null) {
            Integer roleId = user.getRoleId();
            Integer departId = user.getDepartId();

            //创建用户时肯定是关联了角色和部门的，所以如下信息正常不会为空
            RoleEntity role = roleService.selectById(roleId);
            DepartmentEntity department = departmentService.selectById(departId);
            List<AuthEntity> authList = null;
            if (department != null) {
                List<Integer> ids = Arrays.asList(department.getAuthIds().split(","))
                        .stream()
                        .map(item -> Integer.valueOf(item))
                        .collect(Collectors.toList());

                authList = authService.selectByIds(ids);
            }
            return new UserRelationEntity(user, authList, role);
        }
        return null;
    }
}
