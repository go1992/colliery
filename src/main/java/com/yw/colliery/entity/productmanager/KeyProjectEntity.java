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
public class KeyProjectEntity implements Serializable{


    private static final long serialVersionUID = 3428889793108864238L;

    private Integer id;

    /**
     * 班次
     */
    private String shift;

    /**
     * 巷道名称
     */
    private String laneName;

    /**
     * 采区名称
     */
    private String mineAreaName;

    /**
     * 施工队
     */
    private String constructionTeam;

    /**
     * 工程总量
     */
    private String totalEngineering;

    /**
     * 班进度
     */
    private String classSchedule;

    /**
     * 累计完成
     */
    private String cumulativeCompletion;

    /**
     * 剩余工作量
     */
    private String remainingWork;

    /**
     * 值班调度员
     */
    private String scheduleOperator;

    /**
     * 备注
     */
    private String remark;

    /**
     * 日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;


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
