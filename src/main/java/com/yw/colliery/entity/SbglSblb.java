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
 * 设备管理-设备列表
 * </p>
 *
 * @author jobob
 * @since 2019-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="SbglSblb对象", description="设备管理-设备列表")
public class SbglSblb implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


    @ApiModelProperty(value = "排查日期",example = "2012-12-26")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date pcrq;


	@ApiModelProperty(value = "数据所属煤矿",example = "数据所属煤矿")
	private String ssmk;


	@ApiModelProperty(value = "预留字段a",example = "预留字段a")
	private String ylzda;


	@ApiModelProperty(value = "预留字段b",example = "预留字段b")
	private String ylzdb;


	@ApiModelProperty(value = "预留字段c",example = "预留字段c")
	private String ylzdc;


	@ApiModelProperty(value = "设备编码",example = "设备编码")
	private String sbbm;


	@ApiModelProperty(value = "设备名称",example = "设备名称")
	private String sbmc;


	@ApiModelProperty(value = "规格型号",example = "规格型号")
	private String ggxh;


	@ApiModelProperty(value = "生产厂家",example = "生产厂家")
	private String sccj;


	@ApiModelProperty(value = "出厂日期",example = "出厂日期")
	private String ccrq;


	@ApiModelProperty(value = "出厂编号",example = "出厂编号")
	private String ccbh;


	@ApiModelProperty(value = "主要技术参数",example = "主要技术参数")
	private String zyjscs;


	@ApiModelProperty(value = "原值",example = "原值")
	private String yzdd;


	@ApiModelProperty(value = "领交日期",example = "领交日期")
	private String ljrq;


	@ApiModelProperty(value = "领交简要说明",example = "领交简要说明")
	private String ljjysm;


	@ApiModelProperty(value = "使用地点",example = "使用地点")
	private String sydd;


	@ApiModelProperty(value = "设备状态",example = "设备状态")
	private String sbzt;


	@ApiModelProperty(value = "包机人",example = "包机人")
	private String bjrdd;


	@ApiModelProperty(value = "使用单位",example = "使用单位")
	private String sydw;


	@ApiModelProperty(value = "使用方式",example = "使用方式")
	private String syfs;


	@ApiModelProperty(value = "备注",example = "备注")
	private String bzdd;


}
