package com.yw.colliery.entity.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author renzhiqiang
 * @Description 角色实体
 * @Date 2019-04-30
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = -4341597103020122133L;

    private Integer id;

    private String roleName;

    private Integer authLevel;

    private String createUser;

    private Date createDate;

    private String modifyUser;

    private Date modifyDate;
}
