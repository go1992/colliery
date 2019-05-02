package com.yw.colliery.entity.user;

import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.entity.role.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 用户全部信息
 * @Date 2019-05-01
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 2844749949913628495L;
    private CollierySafetyUserEntity safetyUserEntity;

    private RoleEntity roleEntity;

    private DepartmentEntity departmentEntity;

    private List<AuthEntity> authEntityList;
}
