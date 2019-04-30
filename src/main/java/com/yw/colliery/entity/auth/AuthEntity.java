package com.yw.colliery.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author renzhiqiang
 * @Description 权限实体
 * @Date 2019-04-30
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthEntity implements Serializable {
    private static final long serialVersionUID = 2287831234766376297L;
    private Integer id;

    private String name;

    private Integer level;

    private String createUser;

    private Date createDate;

    private String modifyUser;

    private Date modifyDate;
}
