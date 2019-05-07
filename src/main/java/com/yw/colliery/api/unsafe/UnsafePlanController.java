package com.yw.colliery.api.unsafe;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.api.base.EPage;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.entity.unsafe.UnsafePlanEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.service.unsafe.UnsafePlanService;
import com.yw.colliery.service.unsafe.UnsafeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author xuzhou
 * @deprecated
 */
@RestController
@RequestMapping("/apiv1/unsafePlan")
@Slf4j
public class UnsafePlanController implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UnsafePlanService unsafePlanService;

    private static final ConcurrentHashMap<String,String> COLUMN_NAME_MAP = new ConcurrentHashMap<>();


    /**
     * 隐患数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    public ResultDTO input(@RequestBody String data) {
        try {
            UserRelationEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
            UnsafePlanEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafePlanEntity.class);
            unsafeInfoEntity.setCreateUser(user.getSafetyUser().getUsername());
//            unsafeInfoEntity.setCreateUser("xz");
            unsafeInfoEntity.setCreateDate(new Date());
            Integer integer = unsafePlanService.unsafePlanInsert(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化隐患排查计划数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化隐患排查计划数据成功");
        } catch (Exception e) {
            log.error("保存隐患排查数据异常", e);
            return new ResultDTO(ResultDTO.FAILED, "保存隐患排查数据异常");
        }

    }

    /**
     * 获取所有未处理的隐患数据
     *
     * @param
     * @return
     */
    @PostMapping("/get")
    public Object getAllUnsafeInfo(@RequestBody String data) {
        try {
            UserRelationEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
            log.info("data>>>>>"+data);

            UnsafePlanEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafePlanEntity.class);
            //列名转换一下
            if(unsafeInfoEntity.getOrderName() != null){
                unsafeInfoEntity.setOrderName(COLUMN_NAME_MAP.get(unsafeInfoEntity.getOrderName()));
            }
            PageBean unsafePlanInfo = unsafePlanService.getUnsafePlanInfo(unsafeInfoEntity, Optional.ofNullable(unsafeInfoEntity.getPageNum()).orElse(0),Optional.ofNullable(unsafeInfoEntity.getPageSize()).orElse(0));
            log.info("pageNum>>>>>"+unsafeInfoEntity.getPageNum());
            HashMap<String, Object> resultMap = new HashMap<>();
            if (unsafePlanInfo.getList().isEmpty()) {
                return new ResultDTO(ResultDTO.FAILED, "未查询到隐患排查计划数据");
            }

            resultMap.put("total",unsafePlanInfo.getTotal());
            resultMap.put("rows",unsafePlanInfo.getList());
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
    public ResultDTO submit(@RequestBody String data) {
        try {
            UserRelationEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
            UnsafePlanEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafePlanEntity.class);
            unsafeInfoEntity.setModifyUser(user.getSafetyUser().getUsername());
//            unsafeInfoEntity.setModifyUser("xz");
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
    public ResultDTO delete(@RequestBody String data) {
        try {
            UserRelationEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        COLUMN_NAME_MAP.put("planId","plan_id");
        COLUMN_NAME_MAP.put("content","plan_content");
        COLUMN_NAME_MAP.put("planName","plan_name");
        COLUMN_NAME_MAP.put("type","plan_type");
        COLUMN_NAME_MAP.put("departName","plan_depart_name");
        COLUMN_NAME_MAP.put("planDate","plan_date");
    }
}
