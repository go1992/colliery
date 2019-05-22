package com.yw.colliery.sdk.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @auther: xuzhou-013
 * @Date: 2019/5/22 10:14
 * @Description: 用户查询参数列表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam extends BaseParam implements Serializable {

    private static final long serialVersionUID = 3750139923192300937L;

    private String roleId;

    private String departId;

    private String userName;

    private String userPwd;
}
