package com.yw.colliery.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xuzhou-013
 * @Date: 2019/7/28 17:20
 * @Description: 文件参数DTO
 */
@Data
public class FileWhiteListRequestDTO implements Serializable {

    private static final long serialVersionUID = -5884235156335819788L;

    private String coalName;

    private String systemName;

    private String menuName;

    private String type;

    private String fileName;
}
