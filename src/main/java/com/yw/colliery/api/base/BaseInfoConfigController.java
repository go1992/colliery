package com.yw.colliery.api.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.base.BaseInfoConfigEntity;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.base.BaseInfoConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/base/config")
@Slf4j
public class BaseInfoConfigController {

    @Autowired
    private BaseInfoConfigService baseInfoConfigService;

    @RequestMapping("/save")
    public ResultDTO save(@RequestBody String data){
        try {
            List<BaseInfoConfigEntity> baseInfoConfigEntities = JSON.parseArray(data, BaseInfoConfigEntity.class);
            Integer result = baseInfoConfigService.saveBaseConfig(baseInfoConfigEntities);
            if (result < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化配置信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化配置信息成功");
        } catch (Exception e) {
            log.error("持久化配置信息异常",e);
            return new ResultDTO(ResultDTO.SUCCESS, "持久化配置信息异常");
        }
    }

    @RequestMapping("/delete")
    public ResultDTO delete(@RequestBody String data){
        try {
            List<String> arrayLists = JSON.parseArray(data, String.class);
            Integer integer = baseInfoConfigService.deleteConfig(arrayLists);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除配置信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除配置信息成功");
        } catch (Exception e) {
            return new ResultDTO(ResultDTO.SUCCESS, "删除配置信息异常");
        }
    }

    @RequestMapping("/get")
    public Object get(@RequestBody String data){
        try {
            BaseInfoConfigEntity baseInfoConfigEntity = JSONObject.toJavaObject(JSON.parseObject(data), BaseInfoConfigEntity.class);

            PageBean<BaseInfoConfigEntity> baseInfoConfigEntityPageBean = baseInfoConfigService.getAllConfigByCondition(baseInfoConfigEntity
                    , Optional.ofNullable(baseInfoConfigEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(baseInfoConfigEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", baseInfoConfigEntityPageBean.getTotal());
            resultMap.put("rows", baseInfoConfigEntityPageBean.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取配置信息异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取配置信息异常");
        }
    }

    @RequestMapping("/update")
    public ResultDTO update(@RequestBody String data){
        try {
            JSONObject jsonObject = JSONObject.parseObject(data);
            BaseInfoConfigEntity object = JSON.toJavaObject(jsonObject, BaseInfoConfigEntity.class);
            Integer result = baseInfoConfigService.updateBaseConfig(object);
            if (result < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新配置信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新化配置信息成功");
        } catch (Exception e) {
            log.error("更新配置信息异常",e);
            return new ResultDTO(ResultDTO.SUCCESS, "更新配置信息异常");
        }
    }


}
