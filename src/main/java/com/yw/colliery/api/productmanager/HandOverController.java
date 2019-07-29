package com.yw.colliery.api.productmanager;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CaseFormat;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.productmanager.HandOverEntity;
import com.yw.colliery.entity.productmanager.KeyProjectEntity;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.productmanager.impl.HandOverServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author xuzhou
 * @deprecated
 */
@RestController
@RequestMapping("/apiv1/handover")
@Slf4j
public class HandOverController implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private HandOverServiceImpl handOverService;

    private static final ConcurrentHashMap<String, String> COLUMN_NAME_MAP = new ConcurrentHashMap<>();


    /**
     * 调度员交接班数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.HIGH)
    public ResultDTO input(@RequestBody String data) {
        try {
            HandOverEntity handOverEntity = JSONObject.toJavaObject(JSON.parseObject(data), HandOverEntity.class);
            Integer integer = handOverService.insert(handOverEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化调度员交接班数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化调度员交接班数据成功");
        } catch (Exception e) {
            log.error("保存调度员交接班数据异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "保存调度员交接班数据异常");
        }

    }

    /**
     * 获取某部门的调度员交接班数据
     *
     * @param
     * @return
     */
    @PostMapping("/get")
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.LOW)
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            HandOverEntity handOverEntity = JSONObject.toJavaObject(JSON.parseObject(data), HandOverEntity.class);
            //列名转换一下
            if (handOverEntity.getOrderName() != null && COLUMN_NAME_MAP.containsKey(handOverEntity.getOrderName())) {
                handOverEntity.setOrderName(COLUMN_NAME_MAP.get(handOverEntity.getOrderName()));
            }
            PageBean<HandOverEntity> unsafeInfoByUnsafeInfoEntity = handOverService.getByCondition(handOverEntity
                    , Optional.ofNullable(handOverEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(handOverEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafeInfoByUnsafeInfoEntity.getTotal());
            resultMap.put("rows", unsafeInfoByUnsafeInfoEntity.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取调度员交接班列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取调度员交接班列表异常");
        }


    }


    /**
     * 删除调度员交接班计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    public ResultDTO delete(@RequestBody String data) {
        try {
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = handOverService.delete(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除调度员交接班信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除调度员交接班信息成功");
        } catch (Exception e) {
            log.error("删除调度员交接班信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除调度员交接班排信息异常");
        }

    }

    /**
     * 更新调度员交接班计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    public ResultDTO submit(@RequestBody String data) {
        try {
            HandOverEntity handOverEntity = JSONObject.toJavaObject(JSON.parseObject(data), HandOverEntity.class);
            Integer integer = handOverService.update(handOverEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新调度员交接班排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新调度员交接班排查计划信息成功");
        } catch (Exception e) {
            log.error("更新调度员交接班排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新调度员交接班排查计划信息异常");
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Field[] fields = HandOverEntity.class.getDeclaredFields();
        for (Field field : fields) {
            String to = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
            COLUMN_NAME_MAP.put(field.getName(),to);
        }
    }

}
