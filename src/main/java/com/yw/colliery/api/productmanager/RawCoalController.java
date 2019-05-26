package com.yw.colliery.api.productmanager;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.productmanager.RawCoalEntity;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.service.productmanager.impl.RawCoalServiceImpl;
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
@RequestMapping("/apiv1/raw/coal")
@Slf4j
public class RawCoalController implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RawCoalServiceImpl rawCoalService;

    private static final ConcurrentHashMap<String, String> COLUMN_NAME_MAP = new ConcurrentHashMap<>();


    /**
     * 原煤产量台账数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.HIGH)
    public ResultDTO input(@RequestBody String data) {
        try {
            RawCoalEntity rawCoalEntity = JSONObject.toJavaObject(JSON.parseObject(data), RawCoalEntity.class);
            Integer integer = rawCoalService.insert(rawCoalEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化原煤产量台账数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化原煤产量台账数据成功");
        } catch (Exception e) {
            log.error("保存原煤产量台账数据异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "保存原煤产量台账数据异常");
        }

    }

    /**
     * 获取某部门的原煤产量台账数据
     *
     * @param
     * @return
     */
    @PostMapping("/get")
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.LOW)
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            RawCoalEntity rawCoalEntity = JSONObject.toJavaObject(JSON.parseObject(data), RawCoalEntity.class);
            //列名转换一下
            if (rawCoalEntity.getOrderName() != null && COLUMN_NAME_MAP.containsKey(rawCoalEntity.getOrderName())) {
                rawCoalEntity.setOrderName(COLUMN_NAME_MAP.get(rawCoalEntity.getOrderName()));
            }
            PageBean<RawCoalEntity> unsafeInfoByUnsafeInfoEntity = rawCoalService.getByCondition(rawCoalEntity
                    , Optional.ofNullable(rawCoalEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(rawCoalEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafeInfoByUnsafeInfoEntity.getTotal());
            resultMap.put("rows", unsafeInfoByUnsafeInfoEntity.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取原煤产量台账列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取原煤产量台账列表异常");
        }


    }


    /**
     * 删除原煤产量台账计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    public ResultDTO delete(@RequestBody String data) {
        try {
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = rawCoalService.delete(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除原煤产量台账信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除原煤产量台账信息成功");
        } catch (Exception e) {
            log.error("删除原煤产量台账信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除原煤产量台账排信息异常");
        }

    }

    /**
     * 更新原煤产量台账计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    public ResultDTO submit(@RequestBody String data) {
        try {
            RawCoalEntity rawCoalEntity = JSONObject.toJavaObject(JSON.parseObject(data), RawCoalEntity.class);
            Integer integer = rawCoalService.update(rawCoalEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新原煤产量台账排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新原煤产量台账排查计划信息成功");
        } catch (Exception e) {
            log.error("更新原煤产量台账排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新原煤产量台账排查计划信息异常");
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
