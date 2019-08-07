package com.yw.colliery.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xuzhou-013
 * @Date: 2019/7/28 17:20
 * @Description: 文件参数DTO
 */
@Data
public class FileWhiteListRequestDTO extends FileParamsDTO {

    private String order;

    private Integer pageNum;

    private Integer pageSize;
}
