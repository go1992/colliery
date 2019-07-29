package com.yw.colliery.entity.securityrisk;

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
 * 安全风险管控-管控效果及分析
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
@ApiModel(value="AqfxGkxg对象", description="安全风险管控-管控效果及分析")
public class AqfxGkxg implements Serializable {

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


	@ApiModelProperty(value = "组织人 ",example = "组织人 ")
	private String zzr;


	@ApiModelProperty(value = "职务 ",example = "职务 ")
	private String zhiwu;


	@ApiModelProperty(value = "类别 ",example = "类别 ")
	private String leibie;


	@ApiModelProperty(value = "管控重点实施情况 ",example = "管控重点实施情况 ")
	private String gkzdssqk;


	@ApiModelProperty(value = "存在问题 ",example = "存在问题 ")
	private String czwt;


	@ApiModelProperty(value = "管控措施调整情况 ",example = "管控措施调整情况 ")
	private String gkcsdzqk;


	@ApiModelProperty(value = "月度重点 ",example = "月度重点 ")
	private String ydzd;


	@ApiModelProperty(value = "责任分工 ",example = "责任分工 ")
	private String zrfg;


}
