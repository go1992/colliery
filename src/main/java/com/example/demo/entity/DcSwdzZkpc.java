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
 * 地测信息管理-水文地质钻孔排查
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
@ApiModel(value="DcSwdzZkpc对象", description="地测信息管理-水文地质钻孔排查")
public class DcSwdzZkpc implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "主键id",example = "1",required=true)
    @TableId(value = "id", type = IdType.AUTO)
	private Long id;


	@ApiModelProperty(value = "数据所属煤矿",example = "数据所属煤矿")
	private String ssmk;


    @ApiModelProperty(value = "排查日期",example = "2012-12-26")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date pcrq;


    @ApiModelProperty(value = "开孔日期",example = "2012-12-26")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date kkrq;


	@TableField(exist = false,select = true)
	private DcJbcsKqwz zkwzid;

	@ApiModelProperty(value = "钻孔位置")
	@TableField(condition = "")//将此字段表示为条件查询
	private int zkwz;


	@ApiModelProperty(value = "钻孔编号",example = "钻孔编号")
	private String zkbh;


	@ApiModelProperty(value = "水位情况",example = "水位情况")
	private String swqk;


	@ApiModelProperty(value = "孔内状况",example = "孔内状况")
	private String knzk;


	@ApiModelProperty(value = "孔口情况",example = "孔口情况")
	private String kkqk;


	@ApiModelProperty(value = "设计孔深",example = "设计孔深")
	private String sjks;


	@ApiModelProperty(value = "实际孔深",example = "实际孔深")
	private String shijks;


	@ApiModelProperty(value = "记录人",example = "记录人")
	private String jlr;


	@ApiModelProperty(value = "审核人",example = "审核人")
	private String shhr;


	@ApiModelProperty(value = "有无险情",example = "有无险情")
	private String ywxq;


	@ApiModelProperty(value = "预留字段a",example = "预留字段a")
	private String ylzda;


	@ApiModelProperty(value = "预留字段b",example = "预留字段b")
	private String ylzdb;


	@ApiModelProperty(value = "预留字段c",example = "预留字段c")
	private String ylzdc;


}
