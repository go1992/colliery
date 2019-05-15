package com.yw.colliery.api.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.base.BaseConfigEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/config")
public class BaseConfigController {

    @RequestMapping("/save")
    public ResultDTO save(String data){
        JSONObject jsonObject = JSONObject.parseObject(data);
        BaseConfigEntity object = JSON.toJavaObject(jsonObject, BaseConfigEntity.class);
        return null;
    }

    @RequestMapping("/delete")
    public ResultDTO delete(String data){
        return null;
    }

    @RequestMapping("/get")
    public Object get(String data){
        return null;
    }

    @RequestMapping("/update")
    public ResultDTO update(String data){
        return null;
    }


}
