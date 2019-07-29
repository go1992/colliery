package com.yw.colliery.entity.productmanager;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SafetyInfoEntity implements Serializable{


    private static final long serialVersionUID = -6551161318243261132L;
    private Integer id;

    /**
     * 班次
     */
    private String shift;

    /**
     * 日期
     *
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    /**
     * '值班矿长'
     */
    private String dutyManager;

    /**
     * 井下带班矿长
     */
    private String mineManager;

    /**
     * 调度值班员
     */
    private String schedulingOperater;

    /**
     * 监控值班员
     */
    private String monitorOperater;

    /**
     * 产量
     */
    private String output="0";

    /**
     * 入井人数
     */
    private String minePeoples;

    /**
     * 工作情况
     */
    private String workingCondition;

    /**
     * 采面推进情况
     */
    private String miningAdvancementCondition;

    /**
     * 掘进进尺
     */
    private String diggingLength;

    /**
     * 维修进尺
     */
    private String maintenanceLength;

    /**
     * 日产量
     */
    private String dailyOutput;

    /**
     * 日掘进进尺
     */
    private String dailyDiggingLength;

    /**
     * 日维修进尺
     */
    private String dailyMaintenanceLength;

    /**
     * 填表人
     */
    private String createUser;

    /**
     * 填表时间
     */
    private Date createDate;

    /**
     * 签字
     */
    private String sign;

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
