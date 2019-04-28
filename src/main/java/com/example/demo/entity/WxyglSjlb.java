package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 危险源管理-数据列表
 * </p>
 *
 * @author jobob
 * @since 2019-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="WxyglSjlb对象", description="危险源管理-数据列表")
public class WxyglSjlb implements Serializable {

    private static final long serialVersionUID = 1L;

	@TableField(exist=false)
	private Integer shuliang;

    @ApiModelProperty(value = "主键id",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


    @ApiModelProperty(value = "填报日期",example = "2012-12-26")
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


	@ApiModelProperty(value = "企业名称",example = "企业名称")
	private String qymc;


	@ApiModelProperty(value = "企业编号",example = "企业编号")
	private String qybh;


	@ApiModelProperty(value = "重大危险源名称",example = "重大危险源名称")
	private String zdwxymc;


	@ApiModelProperty(value = "重大危险源等级",example = "重大危险源等级")
	private String zdwxydj;


	@ApiModelProperty(value = "重大危险源类型",example = "重大危险源类型")
	private String zdwxylx;


	@ApiModelProperty(value = "重大危险源编码",example = "重大危险源编码")
	private String zdwxybm;


	@ApiModelProperty(value = "是否备案",example = "是否备案")
	private String sfba;


	@ApiModelProperty(value = "备案编号",example = "备案编号")
	private String babh;


    @ApiModelProperty(value = "备案日期",example = "2012-12-26")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date barq;


	@ApiModelProperty(value = "填报人姓名",example = "填报人姓名")
	private String tbrxm;


	@ApiModelProperty(value = "填报人电话",example = "填报人电话")
	private String tbrdh;


	@ApiModelProperty(value = "周边环境",example = "周边环境")
	private String zbhj;


}
