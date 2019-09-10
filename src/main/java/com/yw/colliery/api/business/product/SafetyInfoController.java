package com.yw.colliery.api.business.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.productmanager.SafetyInfoEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.StringUtil;
import com.yw.colliery.service.product.impl.SafetyInfoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author xuzhou
 * @Deprecated 安全信息数据处理
 */
@RestController
@RequestMapping("/apiv1/safety")
@Slf4j
public class SafetyInfoController {

    @Autowired
    private SafetyInfoServiceImpl safetyInfoService;


    /**
     * 安全信息数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE)
    public ResultDTO input(@RequestBody String data) {
        try {
            SafetyInfoEntity safetyInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), SafetyInfoEntity.class);
            Integer integer = safetyInfoService.insert(safetyInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化安全信息数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化安全信息数据成功");
        } catch (Exception e) {
            log.error("保存安全信息数据异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "保存安全信息数据异常");
        }

    }

    /**
     * 获取某部门的安全信息数据
     *
     * @param
     * @return
     */
    @PostMapping("/get")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE, level = AuthConstant.Level.LOW)
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            SafetyInfoEntity safetyInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), SafetyInfoEntity.class);
            //列名转换一下
            if (safetyInfoEntity.getOrderName() != null) {
                safetyInfoEntity.setOrderName(StringUtil.camelToUnderline(safetyInfoEntity.getOrderName()));
            }
            PageBean<SafetyInfoEntity> unsafeInfoByUnsafeInfoEntity = safetyInfoService.getByCondition(safetyInfoEntity
                    , Optional.ofNullable(safetyInfoEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(safetyInfoEntity.getPageSize()).orElse(0));
            List<SafetyInfoEntity> list = unsafeInfoByUnsafeInfoEntity.getList();
            if (!list.isEmpty()){
                String sumOutPut = String.valueOf(list.stream().mapToDouble(entity->Double.valueOf(entity.getOutput())).sum());
                String sumDiggingLength = String.valueOf(list.stream().mapToDouble(entity->Double.valueOf(entity.getDiggingLength())).sum());
                String sumMaintenanceLength = String.valueOf(list.stream().mapToDouble(entity->Double.valueOf(entity.getMaintenanceLength())).sum());
                list.forEach(r -> {
                    r.setDailyOutput(sumOutPut);
                    r.setDailyDiggingLength(sumDiggingLength);
                    r.setDailyMaintenanceLength(sumMaintenanceLength);
                });
            }
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafeInfoByUnsafeInfoEntity.getTotal());
            resultMap.put("rows", unsafeInfoByUnsafeInfoEntity.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取安全信息列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取安全信息列表异常");
        }


    }


    /**
     * 删除安全信息计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE)
    public ResultDTO delete(@RequestBody String data) {
        try {
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = safetyInfoService.delete(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除安全信息信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除安全信息信息成功");
        } catch (Exception e) {
            log.error("删除安全信息信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除安全信息排信息异常");
        }

    }

    /**
     * 更新安全信息计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE)
    public ResultDTO submit(@RequestBody String data) {
        try {
            SafetyInfoEntity safetyInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), SafetyInfoEntity.class);
            Integer integer = safetyInfoService.update(safetyInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新安全信息排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新安全信息排查计划信息成功");
        } catch (Exception e) {
            log.error("更新安全信息排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新安全信息排查计划信息异常");
        }

    }
}
