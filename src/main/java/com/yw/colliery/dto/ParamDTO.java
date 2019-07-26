package com.yw.colliery.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xuzhou
 * @Date: 2019/7/26 15:43
 * @Description: 前端参数DTO
 */
@Data
public class ParamDTO implements Serializable {

    /**
     * 是否分页
     */
    private String isPage;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 日期类型
     */
    private String datetype;

    /**
     * 主键Id
     */
    private String id;

    /**
     * 模糊搜索字段名
     */
    private String fields;

    /**
     * 搜索参数
     */
    private String searchParam;

    /**
     * 排序参数
     *
     */
    private String order;

    /**
     * 排序字段名称
     */
    private String orderName;
}
