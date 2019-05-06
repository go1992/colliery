package com.yw.colliery.service.user.impl;

import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.entity.role.RoleEntity;
import com.yw.colliery.entity.user.CollierySafetyUserEntity;
import com.yw.colliery.entity.user.UserEntity;
import com.yw.colliery.service.auth.AuthService;
import com.yw.colliery.service.depart.DepartmentService;
import com.yw.colliery.service.role.RoleService;
import com.yw.colliery.service.user.CollierySafetyUserService;
import com.yw.colliery.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author renzhiqiang
 * @Description 用户实现类
 * @Date 2019-05-01
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CollierySafetyUserService collierySafetyUserService;

    @Override
    public UserEntity selectByUserId(Integer userId) {
        CollierySafetyUserEntity user = collierySafetyUserService.selectyUserId(userId);
        return handUser(user);
    }

    @Override
    public UserEntity selectByUserName(String userName) {
        CollierySafetyUserEntity user = collierySafetyUserService.selectByUserName(userName);
        return handUser(user);
    }

    @Override
    public List<UserEntity> selectAll() {
        List<CollierySafetyUserEntity> allSafeUser = collierySafetyUserService.selectAllUser();
        List<UserEntity> allUser = new ArrayList<>();
        for (CollierySafetyUserEntity safeUser : allSafeUser) {
            UserEntity userEntity = handUser(safeUser);
            if (userEntity != null){
                allUser.add(userEntity);
            }
        }
        return allUser;
    }

    private UserEntity handUser(CollierySafetyUserEntity user) {
        if (user != null) {
            Integer roleId = user.getRoleId();
            Integer departId = user.getDepartId();

            //创建用户时肯定是关联了角色和部门的，所以如下信息正常不会为空
            RoleEntity role = roleService.selectById(roleId);
            DepartmentEntity department = departmentService.selectById(departId);
            List<AuthEntity> authList = new ArrayList<>();
            if (role != null && department != null) {
                List<Integer> ids = Arrays.asList(department.getAuthIds().split(","))
                        .stream()
                        .map(item -> Integer.valueOf(item))
                        .collect(Collectors.toList());

                authList = authService.selectByLevelAndIds(role.getAuthLevel(), ids);
            }
            return new UserEntity(user, role, department, authList);
        }
        return null;
    }

}
