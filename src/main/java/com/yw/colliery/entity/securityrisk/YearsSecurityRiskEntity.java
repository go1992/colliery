package com.yw.colliery.entity.securityrisk;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 安全风险管控-年度风险辨识
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class YearsSecurityRiskEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id ", example = "1", required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    @ApiModelProperty(value = "辨识时间 ", example = "2012-12-26")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date identificationDate;

    @ApiModelProperty(value = "数据所属煤矿 ", example = "数据所属煤矿 ")
    private String coalMineName;


    @ApiModelProperty(value = "预留字段a ", example = "预留字段a ")
    private String reservedField1;


    @ApiModelProperty(value = "预留字段b ", example = "预留字段b ")
    private String reservedField2;


    @ApiModelProperty(value = "预留字段c ", example = "预留字段c ")
    private String reservedField3;


    @ApiModelProperty(value = "风险点 ", example = "风险点 ")
    private String riskPoint;


    @ApiModelProperty(value = "地图位置定点 ", example = "地图位置定点 ")
    private String mapLocation;


    @ApiModelProperty(value = "责任单位 ", example = "责任单位 ")
    private String dutyDepartment;


    @ApiModelProperty(value = "风险等级 ", example = "风险等级 ")
    private String riskLevel;


    @ApiModelProperty(value = "风险分类 ", example = "风险分类 ")
    private String riskSort;


    @ApiModelProperty(value = "限制人数 ", example = "限制人数 ")
    private String limitPeoples;


    @ApiModelProperty(value = "风险描述 ", example = "风险描述 ")
    private String riskDesc;


    @ApiModelProperty(value = "管控措施 ", example = "管控措施 ")
    private String controlMeasures;


    @ApiModelProperty(value = "主要负责人 ", example = "主要负责人 ")
    private String mainDutyPerson;


    @ApiModelProperty(value = "分管负责人 ", example = "分管负责人 ")
    private String secondDutyPerson;


    @ApiModelProperty(value = "类型(年度风险,专项风险)", example = "类型(年度风险,专项风险)")
    private String type;

    @ApiModelProperty(value = "辨识时间类型", example = "2019(年度风险) 2019-05(月度风险)")
    private String dateType;
}
