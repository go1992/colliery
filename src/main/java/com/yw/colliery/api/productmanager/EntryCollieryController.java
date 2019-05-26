package com.yw.colliery.api.productmanager;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.productmanager.EntryCollieryEntity;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.productmanager.impl.EntryCollieryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author xuzhou
 * @deprecated
 */
@RestController
@RequestMapping("/apiv1/entry/colliery")
@Slf4j
public class EntryCollieryController implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private EntryCollieryServiceImpl entryCollieryService;

    private static final ConcurrentHashMap<String, String> COLUMN_NAME_MAP = new ConcurrentHashMap<>();


    /**
     * 调度人员入井记录数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.HIGH)
    public ResultDTO input(@RequestBody String data) {
        try {
            EntryCollieryEntity EntryCollieryEntity = JSONObject.toJavaObject(JSON.parseObject(data), EntryCollieryEntity.class);
            Integer integer = entryCollieryService.insert(EntryCollieryEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化调度人员入井记录数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化调度人员入井记录数据成功");
        } catch (Exception e) {
            log.error("保存调度人员入井记录数据异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "保存调度人员入井记录数据异常");
        }

    }

    /**
     * 获取某部门的调度人员入井记录数据
     *
     * @param
     * @return
     */
    @PostMapping("/get")
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.LOW)
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            EntryCollieryEntity entryCollieryEntity = JSONObject.toJavaObject(JSON.parseObject(data), EntryCollieryEntity.class);
            //列名转换一下
            if (entryCollieryEntity.getOrderName() != null && COLUMN_NAME_MAP.containsKey(entryCollieryEntity.getOrderName())) {
                entryCollieryEntity.setOrderName(COLUMN_NAME_MAP.get(entryCollieryEntity.getOrderName()));
            }
            PageBean<EntryCollieryEntity> unsafeInfoByUnsafeInfoEntity = entryCollieryService.getByCondition(entryCollieryEntity
                    , Optional.ofNullable(entryCollieryEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(entryCollieryEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafeInfoByUnsafeInfoEntity.getTotal());
            resultMap.put("rows", unsafeInfoByUnsafeInfoEntity.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取调度人员入井记录列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取调度人员入井记录列表异常");
        }


    }


    /**
     * 删除调度人员入井记录计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    public ResultDTO delete(@RequestBody String data) {
        try {
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = entryCollieryService.delete(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除调度人员入井记录信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除调度人员入井记录信息成功");
        } catch (Exception e) {
            log.error("删除调度人员入井记录信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除调度人员入井记录排信息异常");
        }

    }

    /**
     * 更新调度人员入井记录计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    public ResultDTO submit(@RequestBody String data) {
        try {
            EntryCollieryEntity EntryCollieryEntity = JSONObject.toJavaObject(JSON.parseObject(data), EntryCollieryEntity.class);
            Integer integer = entryCollieryService.update(EntryCollieryEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新调度人员入井记录排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新调度人员入井记录排查计划信息成功");
        } catch (Exception e) {
            log.error("更新调度人员入井记录排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新调度人员入井记录排查计划信息异常");
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        COLUMN_NAME_MAP.put("dutyManager", "duty_manager");
        COLUMN_NAME_MAP.put("mineManager", "mine_manager");
        COLUMN_NAME_MAP.put("schedulingOperater", "scheduling_operater");
        COLUMN_NAME_MAP.put("monitorOperater", "monitor_operater");
        COLUMN_NAME_MAP.put("minePeoples", "mine_peoples");
        COLUMN_NAME_MAP.put("workingCondition", "working_condition");
        COLUMN_NAME_MAP.put("miningAdvancement_condition", "mining_advancement_condition");
        COLUMN_NAME_MAP.put("diggingLength", "digging_length");
        COLUMN_NAME_MAP.put("maintenanceLength", "maintenance_length");
        COLUMN_NAME_MAP.put("dailyOutput", "daily_output");
        COLUMN_NAME_MAP.put("dailyDiggingLength", "daily_digging_length");
        COLUMN_NAME_MAP.put("dailyMaintenanceLength", "daily_maintenance_length");
        COLUMN_NAME_MAP.put("createUser", "create_user");
        COLUMN_NAME_MAP.put("createDate", "create_date");
    }

}
