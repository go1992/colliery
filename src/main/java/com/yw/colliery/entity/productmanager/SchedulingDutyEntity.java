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
public class SchedulingDutyEntity implements Serializable{


    private static final long serialVersionUID = 8579010169040520253L;


    private Integer id;

    /**
     * 班次
     */
    private String shift;
    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
    /**
     * 交接时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:ss:mmm")
    @JsonFormat(pattern = "yyyy-MM-dd hh:ss:mm", timezone = "GMT+8")
    private Date handoverDate;

    /**
     * 带班领导
     */
    private String classLeader;
    /**
     * 调度员
     */
    private String dispatcher;

    /**
     * 入井人数
     */
    private String minePeoples;

    /**
     * 掘进对
     */
    private String tunnelingTeam;

    /**
     * 机运队
     */
    private String machineTeam;

    /**
     * 管理人员
     */
    private String manager;

    /**
     * 采煤工作面
     */
    private String coalMiningFace;

    /**
     * 掘进工作面
     */
    private String tunnelingFace;

    /**
     * 产量
     */
    private String output;

    /**
     * 掘进进尺
     */
    private String diggingLength;

    /**
     * 维修进尺
     */
    private String maintenanceLength;

    /**
     * 安全事故处理情况
     */
    private String remark1;

    /**
     * 设备运转及故障处理
     */
    private String remark2;

    /**
     * 其他
     */
    private String remark3;


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
