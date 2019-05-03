package com.yw.colliery.api.unsafe;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.entity.unsafe.UnsafePlanEntity;
import com.yw.colliery.entity.user.UserAuthEntity;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.service.unsafe.UnsafePlanService;
import com.yw.colliery.service.unsafe.UnsafeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;


/**
 * @author xuzhou
 * @deprecated
 */
@RestController
@RequestMapping("/apiv1/unsafePlan")
@Slf4j
public class UnsafePlanController {

    @Autowired
    private UnsafePlanService unsafePlanService;


    /**
     * 隐患数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    public ResultDTO input(@RequestBody String data) {
        try {
            UserAuthEntity user = LoginSessionUtils.getUser();
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
            return new ResultDTO(ResultDTO.SUCCESS, "保存隐患排查数据异常");
        }

    }

    /**
     * 获取所有未处理的隐患数据
     *
     * @param
     * @return
     */
    @GetMapping("/get")
    public ResultDTO getAllUnsafeInfo(@RequestBody String data) {
        try {
            UserAuthEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
            UnsafePlanEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafePlanEntity.class);
            ArrayList<UnsafeInfoEntity> allUnsafeInfo = new ArrayList<>(unsafePlanService.getUnsafePlanInfo(unsafeInfoEntity));
            if (allUnsafeInfo.isEmpty()) {
                return new ResultDTO(ResultDTO.SUCCESS, "未查询到隐患排查计划数据");
            }
            return new ResultDTO<>(allUnsafeInfo, ResultDTO.SUCCESS, "获取处理的隐患排计划查成功");
        } catch (Exception e) {
            log.error("获取隐患排查计划列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取隐患隐排查计划列表异常");
        }

    }



    /**
     * 处理隐患数据
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    public ResultDTO submit(@RequestBody String data) {
        try {
            UserAuthEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
            UnsafePlanEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafePlanEntity.class);
            unsafeInfoEntity.setModifyUser(user.getSafetyUser().getUsername());
            unsafeInfoEntity.setModifyDate(new Date());
            Integer integer = unsafePlanService.upateUnsafePlanInfo(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新隐患排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患排查计划信息成功");
        } catch (Exception e) {
            log.error("更新隐患排查计划信息异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患排查计划信息异常");
        }

    }

}
