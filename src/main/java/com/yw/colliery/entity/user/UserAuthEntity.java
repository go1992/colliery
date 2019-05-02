package com.yw.colliery.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 用户权限实体
 * @Date 2019-04-30
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthEntity implements Serializable {
    private static final long serialVersionUID = -1216877393520656236L;
    private  CollierySafetyUserEntity safetyUser;

    private List<Integer> authIds;
}
