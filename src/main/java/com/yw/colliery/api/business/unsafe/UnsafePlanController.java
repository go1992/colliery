package com.yw.colliery.api.business.unsafe;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.unsafe.UnsafePlanEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.StringUtil;
import com.yw.colliery.service.unsafe.UnsafePlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author xuzhou
 * @Deprecated 隐患计划信息处理
 */
@RestController
@RequestMapping("/apiv1/unsafePlan")
@Slf4j
public class UnsafePlanController {

    @Autowired
    private UnsafePlanService unsafePlanService;


    /**
     * 隐患计划数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE)
    public ResultDTO input(@RequestBody String data) {
        try {
            UnsafePlanEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafePlanEntity.class);
            unsafeInfoEntity.setCreateUser("system");
            unsafeInfoEntity.setCreateDate(new Date());
            Integer integer = unsafePlanService.unsafePlanInsert(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化隐患排查计划数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化隐患排查计划数据成功");
        } catch (Exception e) {
            log.error("保存隐患排查数据异常", e);
            return new ResultDTO(ResultDTO.FAILED, "保存隐患排查计划数据异常");
        }

    }

    /**
     * 获取所有隐患计划
     *
     * @param
     * @return
     */
    @PostMapping("/get")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.LOW)
    public Object getAllUnsafeInfo(@RequestBody String data) {
        try {
            UnsafePlanEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafePlanEntity.class);
            //列名转换一下
            if (unsafeInfoEntity.getOrderName() != null) {
                unsafeInfoEntity.setOrderName(StringUtil.camelToUnderline(unsafeInfoEntity.getOrderName()));
            }
            PageBean unsafePlanInfo = unsafePlanService.getUnsafePlanInfo(unsafeInfoEntity, Optional.ofNullable(unsafeInfoEntity.getPageNum()).orElse(0), Optional.ofNullable(unsafeInfoEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafePlanInfo.getTotal());
            resultMap.put("rows", unsafePlanInfo.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取隐患排查计划列表异常", e);
            return new ResultDTO(ResultDTO.FAILED, "获取隐患隐排查计划列表异常");
        }

    }


    /**
     * 更新隐患计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE)
    public ResultDTO submit(@RequestBody String data) {
        try {
            UnsafePlanEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafePlanEntity.class);
            unsafeInfoEntity.setModifyUser("system");
            unsafeInfoEntity.setModifyDate(new Date());
            Integer integer = unsafePlanService.upateUnsafePlanInfo(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新隐患排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患排查计划信息成功");
        } catch (Exception e) {
            log.error("更新隐患排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新隐患排查计划信息异常");
        }

    }

    /**
     * 删除隐患计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE)
    public ResultDTO delete(@RequestBody String data) {
        try {
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = unsafePlanService.deleteUnsafePlanInfo(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除隐患排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除隐患排查计划信息成功");
        } catch (Exception e) {
            log.error("更新隐患排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除隐患排查计划信息异常");
        }

    }
}
