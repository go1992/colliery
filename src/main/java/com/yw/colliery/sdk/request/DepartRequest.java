package com.yw.colliery.sdk.request;

import com.yw.colliery.entity.auth.AuthEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: renzhiqiang
 * @Description: 部门请求封装
 * @Date: 2019/5/7
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartRequest implements Serializable{
    private static final long serialVersionUID = 2935708705058109519L;

    private Integer id;

    private List<String> authIds;

    private String departName;

    private String coalMine;

    private String createUser;

    private Date createDate;

    private String modifyUser;

    private Date modifyDate;
}
