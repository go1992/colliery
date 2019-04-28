package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 地测信息管理-基本参数-用户
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
@ApiModel(value="DcJbcsYh对象", description="地测信息管理-基本参数-用户")
public class DcJbcsYh implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id ",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


	@ApiModelProperty(value = "数据所属煤矿 ",example = "数据所属煤矿 ")
	private String ssmk;


	@ApiModelProperty(value = "预留字段a ",example = "预留字段a ")
	private String ylzda;


	@ApiModelProperty(value = "预留字段b ",example = "预留字段b ")
	private String ylzdb;


	@ApiModelProperty(value = "预留字段c ",example = "预留字段c ")
	private String ylzdc;


	@ApiModelProperty(value = "用户名称 ",example = "用户名称 ")
	private String yhmc;


	@ApiModelProperty(value = "用户密码 ",example = "用户密码 ")
	private String yhmm;


	@ApiModelProperty(value = "用户分类 ",example = "用户分类 ")
	private String yhfl;


	@ApiModelProperty(value = "显示顺序 ",example = "显示顺序 ")
	private String xssx;


	@ApiModelProperty(value = "登陆名称 ",example = "登陆名称 ")
	private String dlmc;


}
