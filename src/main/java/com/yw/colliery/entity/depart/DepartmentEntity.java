package com.yw.colliery.entity.depart;

import com.yw.colliery.entity.auth.AuthEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 部门实体
 * @Date 2019-04-30
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentEntity implements Serializable {
    private static final long serialVersionUID = 5055228492905211996L;
    private Integer id;

    private String authIds;

    private String departName;

    private String coalMine;

    private String createUser;

    private Date createDate;

    private String modifyUser;

    private Date modifyDate;

    List<AuthEntity> authList;
}
