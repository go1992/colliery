package com.yw.colliery.entity.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author renzhiqiang
 * @Description 角色实体
 * @Date 2019-04-30
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileWhiteListEntity implements Serializable {


    private static final long serialVersionUID = 210725084397850605L;

    private Integer id;

    private String coal;

    private String depart;

    private String system;

    private String menu;

    private String type;

    private String fileName;

    @JsonIgnore
    private String orderName;

    @JsonIgnore
    private String order;

}
