package com.yw.colliery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ResultDTO<T extends Serializable> implements Serializable {

    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";

    private T data;

    private String code = SUCCESS;

    private String message;

    public ResultDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean noData(){
        return this.code.equals(FAILED)||this.data==null;
    }
}
