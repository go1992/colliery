package com.yw.colliery.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xuzhou
 * @Date: 2019/5/15 15:34
 * @Description: 基础配置信息实体
 */
@Data
public class BaseInfoConfigEntity implements Serializable {

    private static final long serialVersionUID = -3509376166761115618L;

    private Integer id;

    private String name;

    private String value;

    private Date createDate;

    private String createUser;

    private Date modifyDate;

    private String modifyUser;

    /**
     * 页数
     */
    @JsonIgnore
    private Integer pageNum;
    /**
     * 每页大小
     */
    @JsonIgnore
    private Integer pageSize;
    /**
     * 排序的字段
     */
    @JsonIgnore
    private String orderName;
    /**
     * 升序还是降序
     */
    @JsonIgnore
    private String order;
}
