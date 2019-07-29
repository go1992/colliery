package com.yw.colliery.sdk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * @Auther: xuzhou-013
 * @Date: 2019/7/29 16:05
 * @Description: Bean工具类
 */
public class BeanUtils {

    /**
     *
     * @param bean 转map
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String,Object> beanToMap(T bean){
        JSONObject paramJson = JSONObject.parseObject(JSON.toJSONString(bean));
        return JSONObject.toJavaObject(paramJson, Map.class);
    }

    /**
     * map转Bean
     * @param params
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T mapToBean(Map<String,Object> params,Class<T> clazz){
        JSONObject paramJson = JSON.parseObject(JSON.toJSONString(params));
        return JSONObject.toJavaObject(paramJson, clazz);
    }

}
