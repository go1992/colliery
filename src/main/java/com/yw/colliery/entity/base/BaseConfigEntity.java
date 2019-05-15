package com.yw.colliery.entity.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: xuzhou
 * @Date: 2019/5/15 15:34
 * @Description: 基础配置信息实体
 */
@Data
@AllArgsConstructor
public class BaseConfigEntity implements Serializable {

    private static final long serialVersionUID = -3509376166761115618L;

    private String name;

    private String value;

    private Date creatDate;

    private String creatUser;

    private Date modifyDate;

    private String modifyUser;
}
