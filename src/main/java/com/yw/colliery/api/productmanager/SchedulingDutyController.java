package com.yw.colliery.api.productmanager;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.CaseFormat;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.productmanager.KeyProjectEntity;
import com.yw.colliery.entity.productmanager.SchedulingDutyEntity;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.productmanager.impl.SchedulingDutyServiceImpl;
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
@RequestMapping("/apiv1/schedule/duty")
@Slf4j
public class SchedulingDutyController implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SchedulingDutyServiceImpl schedulingDutyService;

    private static final ConcurrentHashMap<String, String> COLUMN_NAME_MAP = new ConcurrentHashMap<>();


    /**
     * 调度值班记录数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.HIGH)
    public ResultDTO input(@RequestBody String data) {
        try {
            SchedulingDutyEntity schedulingDutyEntity = JSONObject.toJavaObject(JSON.parseObject(data), SchedulingDutyEntity.class);
            Integer integer = schedulingDutyService.insert(schedulingDutyEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化调度值班记录数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化调度值班记录数据成功");
        } catch (Exception e) {
            log.error("保存调度值班记录数据异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "保存调度值班记录数据异常");
        }

    }

    /**
     * 获取某部门的调度值班记录数据
     *
     * @param
     * @return
     */
    @PostMapping("/get")
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.LOW)
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            SchedulingDutyEntity schedulingDutyEntity = JSONObject.toJavaObject(JSON.parseObject(data), SchedulingDutyEntity.class);
            //列名转换一下
            if (schedulingDutyEntity.getOrderName() != null && COLUMN_NAME_MAP.containsKey(schedulingDutyEntity.getOrderName())) {
                schedulingDutyEntity.setOrderName(COLUMN_NAME_MAP.get(schedulingDutyEntity.getOrderName()));
            }
            PageBean<SchedulingDutyEntity> unsafeInfoByUnsafeInfoEntity = schedulingDutyService.getByCondition(schedulingDutyEntity
                    , Optional.ofNullable(schedulingDutyEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(schedulingDutyEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafeInfoByUnsafeInfoEntity.getTotal());
            resultMap.put("rows", unsafeInfoByUnsafeInfoEntity.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取调度值班记录列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取调度值班记录列表异常");
        }


    }


    /**
     * 删除调度值班记录计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    public ResultDTO delete(@RequestBody String data) {
        try {
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = schedulingDutyService.delete(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除调度值班记录信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除调度值班记录信息成功");
        } catch (Exception e) {
            log.error("删除调度值班记录信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除调度值班记录排信息异常");
        }

    }

    /**
     * 更新调度值班记录计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    public ResultDTO submit(@RequestBody String data) {
        try {
            SchedulingDutyEntity schedulingDutyEntity = JSONObject.toJavaObject(JSON.parseObject(data), SchedulingDutyEntity.class);
            Integer integer = schedulingDutyService.update(schedulingDutyEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新调度值班记录排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新调度值班记录排查计划信息成功");
        } catch (Exception e) {
            log.error("更新调度值班记录排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新调度值班记录排查计划信息异常");
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Field[] fields = SchedulingDutyEntity.class.getDeclaredFields();
        for (Field field : fields) {
            String to = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
            COLUMN_NAME_MAP.put(field.getName(),to);
        }
    }
}
