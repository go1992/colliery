package com.yw.colliery.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="ResultMessage", description="返回结果")
public class ResultObject {
	public static String SUCCESS = "success";
	public static String FAILED = "failed";
	
	@ApiModelProperty(value="接口调用状态(success表示成功,failed表示失败)")   
	private String status;
	@ApiModelProperty(value="接口编号")
	private String code;
	@ApiModelProperty(value="接口调用消息")
	private String message;
	@ApiModelProperty(value="接口调用数据结果")
	private Object result;

	public static ResultObject buildSucessResponse(Object result){
		ResultObject response = new ResultObject();
		response.setStatus(SUCCESS);
		response.setResult(result);
		return response;
	}

	public static ResultObject buildFailResponse(String msg){
		ResultObject response = new ResultObject();
		response.setStatus(FAILED);
		response.setMessage(msg);
		return response;
	}
}
