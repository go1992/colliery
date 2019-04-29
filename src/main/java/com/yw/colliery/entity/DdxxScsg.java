package com.yw.colliery.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 调度信息管理-生产安全事故处理追踪
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="DdxxScsg对象", description="调度信息管理-生产安全事故处理追踪")
public class DdxxScsg implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id ",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


    @ApiModelProperty(value = "排查日期 ",example = "2012-12-26")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date pcrq;


	@ApiModelProperty(value = "数据所属煤矿 ",example = "数据所属煤矿 ")
	private String ssmk;


	@ApiModelProperty(value = "预留字段a ",example = "预留字段a ")
	private String ylzda;


	@ApiModelProperty(value = "预留字段b ",example = "预留字段b ")
	private String ylzdb;


	@ApiModelProperty(value = "预留字段c ",example = "预留字段c ")
	private String ylzdc;


	@ApiModelProperty(value = "录入人员 ",example = "录入人员 ")
	private String lrry;


	@ApiModelProperty(value = "施工地点 ",example = "施工地点 ")
	private String sgdd;


	@ApiModelProperty(value = "班次 ",example = "班次 ")
	private String banci;


	@ApiModelProperty(value = "事故原因 ",example = "事故原因 ")
	private String sgyy;


	@ApiModelProperty(value = "事故级别 ",example = "事故级别 ")
	private String sgjb;


	@ApiModelProperty(value = "伤亡人数 ",example = "伤亡人数 ")
	private String swrs;


	@ApiModelProperty(value = "经济损失 ",example = "经济损失 ")
	private String jjss;


	@ApiModelProperty(value = "处理过程 ",example = "处理过程 ")
	private String clgc;


	@ApiModelProperty(value = "领导批示 ",example = "领导批示 ")
	private String ldps;


	@ApiModelProperty(value = "处理结果 ",example = "处理结果 ")
	private String cljg;


	@ApiModelProperty(value = "责任追究 ",example = "责任追究 ")
	private String zrzj;


	@ApiModelProperty(value = "调度员 ",example = "调度员 ")
	private String ddy;


	@ApiModelProperty(value = "监控员 ",example = "监控员 ")
	private String jky;


	@ApiModelProperty(value = "值班领导 ",example = "值班领导 ")
	private String zbld;


	@ApiModelProperty(value = "是否上报上级 ",example = "是否上报上级 ")
	private String sfsbsj;


	@ApiModelProperty(value = "是否处理完毕 ",example = "是否处理完毕 ")
	private String sfclwb;


}
