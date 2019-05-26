package com.yw.colliery.entity.productmanager;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryCollieryEntity implements Serializable{


    private static final long serialVersionUID = -507841789411431347L;
    /**
     * 主键
     */
    private Integer id;

    /**
     * 日期
     */
    private Date date;

    /**
     * 入井人员
     */
    private String minePeople;

    /**
     * 班次
     */
    private String shift;

    /**
     * 地点
     */
    private String location;

    /**
     * 行走路线
     */
    private String route;

    /**
     * 发现问题及落实情况
     */
    private String remark;

    /**
     * 页数
     */
    @JsonIgnore
    private Integer pageNum=0;
    /**
     * 每页大小
     */
    @JsonIgnore
    private Integer pageSize=0;
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
