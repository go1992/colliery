package com.yw.colliery.entity.unsafe;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 隐患排查治理-隐患信息实体
 *
 * @author xuzhou
 * @since 2019-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UnsafeInfoEntity implements Serializable {


    private static final long serialVersionUID = 1L;


    private Long id;
    /**
     * 隐患地点
     */
    private String location;
    /**
     * 处理部门的
     */
    private Integer departId;

    /**
     * 隐患内容
     */
    private String content;

    /**
     * 隐患类型
     */
    private String type;

    /**
     * 隐患级别
     */
    private String level;

    /**
     * 隐患状态
     */
    private String status;


    /**
     * 检查时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkTime;

    /**
     * 所属煤矿
     */
    private String coalMine;

    /**
     * 检查人
     */
    private String checkPerson;

    /**
     * 检查类型
     */
    private String checkType;

    /**
     * 整改日期
     */
    private String reformDate;


    /**
     * 整改措施
     */
    private String reformMeasure;

    /**
     * 责任单位
     */
    private String dutyDepart;

    /**
     * 责任人
     */
    private String dutyPerson;


    /**
     * 罚款理由
     */
    private String fineReason;

    /**
     * 罚款金额
     */
    private String fineMoney;

    /**
     * 罚款查找
     */
    private String fineFind;

    /**
     * 处理过程跟踪
     */
    private String dealProcess;

    /**
     * 创建者
     */
    private String createUser;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改者
     */
    private String modifyUser;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 预留字段1
     */
    private String extra1;

    /**
     * 预留字段2
     */
    private String extra2;

    /**
     * 预留字段3
     */
    private String extra3;
}
