package com.example.demo.entity;

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
 * 调度信息管理-安全生产调度报表
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
@ApiModel(value="DdxxScdd对象", description="调度信息管理-安全生产调度报表")
public class DdxxScdd implements Serializable {

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


	@ApiModelProperty(value = "生产单位 ",example = "生产单位 ")
	private String scdw;


	@ApiModelProperty(value = "班次 ",example = "班次 ")
	private String banci;


	@ApiModelProperty(value = "施工单位人数 ",example = "施工单位人数 ")
	private String sgdwrs;


	@ApiModelProperty(value = "井下全部人数 ",example = "井下全部人数 ")
	private String jxqbrs;


	@ApiModelProperty(value = "产量 ",example = "产量 ")
	private String chanliang;


	@ApiModelProperty(value = "施工地点 ",example = "施工地点 ")
	private String sgdd;


	@ApiModelProperty(value = "进尺 ",example = "进尺 ")
	private String jinchi;


	@ApiModelProperty(value = "安全信息及问题 ",example = "安全信息及问题 ")
	private String aqxxjwt;


	@ApiModelProperty(value = "其他事项 ",example = "其他事项 ")
	private String qtsx;


	@ApiModelProperty(value = "调度员 ",example = "调度员 ")
	private String ddy;


	@ApiModelProperty(value = "监控员 ",example = "监控员 ")
	private String jky;


	@ApiModelProperty(value = "值班领导 ",example = "值班领导 ")
	private String zbld;


	@ApiModelProperty(value = "是否审核 ",example = "是否审核 ")
	private String sfsh;


	@ApiModelProperty(value = "是否事故 ",example = "是否事故 ")
	private String sfsg;


}
