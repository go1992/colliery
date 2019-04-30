package com.yw.colliery.sdk.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 用户权限请求
 * @Date 2019-04-30
 **/
@Data
@ToString
public class UserAuthRequest implements Serializable {

    private static final long serialVersionUID = 2935708705058109509L;

    private Integer userId;

    private String userName;

    private String password;

    private List<String> userAuthors;

    private String createUser;

    private String modifyUser;

    private Date modifyDate;
}
