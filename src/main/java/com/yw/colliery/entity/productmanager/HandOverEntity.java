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
public class HandOverEntity implements Serializable{


    private static final long serialVersionUID = -4213949418391965560L;

    private Integer id;

    /**
     * 班次
     */
    private String shift;

    /**
     * 日期
     */
    private Date date;

    /**
     * 接班调度员
     */
    private String successionDispatcher;

    /**
     * 交班调度员
     */
    private String handoverDispatcher;

    /**
     * 交接时间
     */
    private String handoverDate;

    /**
     * 本班情况及遗留问题
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
