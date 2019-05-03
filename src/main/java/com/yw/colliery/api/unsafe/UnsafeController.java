package com.yw.colliery.api.unsafe;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
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
@RequestMapping("/apiv1/unsafe")
@Slf4j
public class UnsafeController {

    @Autowired
    private UnsafeService unsafeService;


    /**
     * 隐患数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
    public ResultDTO input(@RequestBody String data) {
        try {
            UserRelationEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
            UnsafeInfoEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafeInfoEntity.class);
            unsafeInfoEntity.setCreateUser(user.getSafetyUser().getUsername());
            unsafeInfoEntity.setCreateUser("xz");
            unsafeInfoEntity.setCreateDate(new Date());
            unsafeInfoEntity.setStatus("0");
            Integer integer = unsafeService.unsafeInsert(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化隐患数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化隐患数据成功");
        } catch (Exception e) {
            log.error("保存隐患数据异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "保存隐患数据异常");
        }

    }

    /**
     * 获取所有未处理的隐患数据
     *
     * @param
     * @return
     */
    @GetMapping("/get/all/unsafeInfo")
    @AuthModule(authId = {AuthConstant.Module.UNSAFE_MODULE_WATCH, AuthConstant.Module.UNSAFE_MODULE_SUPER})
    public ResultDTO getAllUnsafeInfo() {
        try {
            ArrayList<UnsafeInfoEntity> allUnsafeInfo = new ArrayList<>(unsafeService.getAllUnsafeInfo());
            if (allUnsafeInfo.isEmpty()) {
                return new ResultDTO(ResultDTO.SUCCESS, "未查询到隐患数据");
            }
            return new ResultDTO<>(allUnsafeInfo, ResultDTO.SUCCESS, "获取未处理的隐患列表成功");
        } catch (Exception e) {
            log.error("获取隐患列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取隐患列表异常");
        }

    }

    /**
     * 获取某部门的隐患数据
     *
     * @param
     * @return
     */
    @GetMapping("/get/depart/unsafeInfo")
    @AuthModule(authId = {AuthConstant.Module.UNSAFE_MODULE_WATCH, AuthConstant.Module.UNSAFE_MODULE_SUPER})
    public ResultDTO getUnsafeInfo() {
        UserRelationEntity user = LoginSessionUtils.getUser();
        if (user == null) {
            return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
        }
        try {
            ArrayList<UnsafeInfoEntity> unsafeInfoByDepartId = new ArrayList<>(unsafeService.getUnsafeInfoByDepartId(user.getSafetyUser().getDepartId()));
//            ArrayList<UnsafeInfoEntity> unsafeInfoByDepartId = new ArrayList<>(unsafeService.getUnsafeInfoByDepartId(1));
            if (unsafeInfoByDepartId.isEmpty()) {
                return new ResultDTO(ResultDTO.SUCCESS, "未查询到隐患数据");
            }
            return new ResultDTO<>(unsafeInfoByDepartId, ResultDTO.SUCCESS, "获取未处理的隐患列表成功");
        } catch (Exception e) {
            log.error("根据部门id获取隐患信息异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取隐患列表异常");
        }


    }

    /**
     * 处理隐患数据
     *
     * @param
     * @return
     */
    @PostMapping("/submit/unsafeInfo")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
    public ResultDTO submit(@RequestParam("id") Long id) {
        UserRelationEntity user = LoginSessionUtils.getUser();
        if (user == null) {
            return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
        }
        try {
            UnsafeInfoEntity unsafeInfoEntity = new UnsafeInfoEntity();
            unsafeInfoEntity.setId(id);
            unsafeInfoEntity.setStatus("1");
            unsafeInfoEntity.setModifyUser("xz");
            unsafeInfoEntity.setModifyDate(new Date());
            Integer integer = unsafeService.upateUnsafeInfo(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新隐患信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患信息成功");
        } catch (Exception e) {
            log.error("更新隐患信息异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患信息异常");
        }

    }

    /**
     * 分发隐患数据
     *
     * @param
     * @return
     */
    @PostMapping("/apply/distributed")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
    public ResultDTO distributed(@RequestParam("id") Long id,@RequestParam("departId") int departId) {
        UserRelationEntity user = LoginSessionUtils.getUser();
        if (user == null) {
            return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
        }
        try {
            UnsafeInfoEntity unsafeInfoEntity = new UnsafeInfoEntity();
            unsafeInfoEntity.setId(id);
            unsafeInfoEntity.setDepartId(departId);
            unsafeInfoEntity.setModifyUser("xz");
            unsafeInfoEntity.setModifyDate(new Date());
            Integer integer = unsafeService.upateUnsafeInfo(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新隐患信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患信息成功");
        } catch (Exception e) {
            log.error("更新隐患信息异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患信息异常");
        }

    }

}
