package com.yw.colliery.entity.user;

import com.yw.colliery.entity.auth.AuthEntity;
import com.yw.colliery.entity.role.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 用户关联信息实体
 * @Date 2019-04-30
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRelationEntity implements Serializable {
    private static final long serialVersionUID = -1216877393520656236L;
    private  CollierySafetyUserEntity safetyUser;

    private List<AuthEntity> authList;

    private RoleEntity role;
}
