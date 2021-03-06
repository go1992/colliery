package com.yw.colliery.api.business.unsafe;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.sdk.utils.StringUtil;
import com.yw.colliery.service.unsafe.UnsafeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author xuzhou
 * @Deprecated 隐患信息管理
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
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE)
    public ResultDTO input(@RequestBody String data) {
        try {
            UnsafeInfoEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafeInfoEntity.class);
            unsafeInfoEntity.setCreateUser("system");
            unsafeInfoEntity.setCreateDate(new Date());
            //未处理
            unsafeInfoEntity.setStatus("0");
            //未分发
            unsafeInfoEntity.setDistributedStatus("0");
            //未签单
            unsafeInfoEntity.setSignStatus("0");
            //发起部门的id
            unsafeInfoEntity.setStartDepartId(2);
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
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.LOW)
    public Object getAllUnsafeInfo() {
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
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.LOW)
    public ResultDTO getUnsafeInfo() {
        UserRelationEntity user = LoginSessionUtils.getUser();
        if (user == null) {
            return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
        }
        try {
            ArrayList<UnsafeInfoEntity> unsafeInfoByDepartId = new ArrayList<>(unsafeService.getUnsafeInfoByDepartId(user.getSafetyUser().getDepartId()));
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
     * 获取某部门的隐患数据
     *
     * @param
     * @return
     */
    @PostMapping("/get/unsafeInfo/condition")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.LOW)
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            UnsafeInfoEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafeInfoEntity.class);
            //列名转换一下
            if (unsafeInfoEntity.getOrderName() != null) {
                unsafeInfoEntity.setOrderName(StringUtil.camelToUnderline(unsafeInfoEntity.getOrderName()));
            }
            PageBean<UnsafeInfoEntity> unsafeInfoByUnsafeInfoEntity = unsafeService.getUnsafeInfoByUnsafeInfoEntity(unsafeInfoEntity
                    , Optional.ofNullable(unsafeInfoEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(unsafeInfoEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafeInfoByUnsafeInfoEntity.getTotal());
            resultMap.put("rows", unsafeInfoByUnsafeInfoEntity.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取隐患列表异常", e);
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
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE)
    public ResultDTO submit(@RequestParam("id") Long id) {
        try {
            UnsafeInfoEntity unsafeInfoEntity = new UnsafeInfoEntity();
            unsafeInfoEntity.setId(id);
            unsafeInfoEntity.setStatus("1");
            unsafeInfoEntity.setModifyUser("system");
            unsafeInfoEntity.setModifyDate(new Date());
            Integer integer = unsafeService.upateUnsafeInfo(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "处理隐患信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "处理隐患信息成功");
        } catch (Exception e) {
            log.error("处理隐患信息异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "处理隐患信息异常");
        }

    }

    /**
     * 分发隐患数据
     *
     * @param
     * @return
     */
    @PostMapping("/apply/distributed")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE)
    public ResultDTO distributed(@RequestParam("id") Long id, @RequestParam("departId") int departId) {
        try {
            UnsafeInfoEntity unsafeInfoEntity = new UnsafeInfoEntity();
            //分发隐患数据的ID
            unsafeInfoEntity.setId(id);
            //分发到部门的id
            unsafeInfoEntity.setDepartId(departId);
            //标记为已分发
            unsafeInfoEntity.setDistributedStatus("1");
            unsafeInfoEntity.setModifyUser("xz");
            //分发的时间
            unsafeInfoEntity.setModifyDate(new Date());
            Integer integer = unsafeService.upateUnsafeInfo(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "分发隐患信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "分发隐患信息成功");
        } catch (Exception e) {
            log.error("分发隐患信息异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "分发隐患信息异常");
        }

    }

    /**
     * 处理隐患数据
     *
     * @param
     * @return
     */
    @PostMapping("/sign")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE)
    public ResultDTO sign(@RequestParam("id") Long id) {
        try {
            UserRelationEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
            UnsafeInfoEntity unsafeInfoEntity = new UnsafeInfoEntity();
            unsafeInfoEntity.setId(id);
            unsafeInfoEntity.setSignStatus("1");
            unsafeInfoEntity.setModifyUser(user.getSafetyUser().getUsername());
            unsafeInfoEntity.setModifyDate(new Date());
            Integer integer = unsafeService.upateUnsafeInfo(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "隐患签单失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "隐患签单成功");
        } catch (Exception e) {
            log.error("隐患签单异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "隐患签单异常");
        }

    }

    /**
     * 删除隐患信息
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE)
    public ResultDTO delete(@RequestBody String data) {
        try {
            UserRelationEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = unsafeService.delete(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除隐患信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除隐患信息成功");
        } catch (Exception e) {
            log.error("更新隐患信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除隐患排信息异常");
        }

    }

    /**
     * 更新隐患信息
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE)
    public ResultDTO submit(@RequestBody String data) {
        try {
            UserRelationEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
            UnsafeInfoEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafeInfoEntity.class);
            unsafeInfoEntity.setModifyUser(user.getSafetyUser().getUsername());
            unsafeInfoEntity.setModifyDate(new Date());
            Integer integer = unsafeService.upateUnsafeInfo(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新隐患信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患信息成功");
        } catch (Exception e) {
            log.error("更新隐患排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新隐患信息异常");
        }

    }
}
