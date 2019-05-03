package com.yw.colliery.api.unsafe;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.entity.user.UserAuthEntity;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.service.depart.DepartmentService;
import com.yw.colliery.service.unsafe.UnsafeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    public ResultDTO input(@RequestBody String data) {
        try {
            UserAuthEntity user = LoginSessionUtils.getUser();
            if (user == null) {
                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
            }
            UnsafeInfoEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafeInfoEntity.class);
            unsafeInfoEntity.setCreateUser(user.getSafetyUser().getUsername());
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
    public ResultDTO getUnsafeInfo() {
        UserAuthEntity user = LoginSessionUtils.getUser();
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
     * 获取某部门的隐患数据
     *
     * @param
     * @return
     */
    @PostMapping("/get/unsafeInfo/condition")
    public ResultDTO getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            UnsafeInfoEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafeInfoEntity.class);
            ArrayList<UnsafeInfoEntity> unsafeInfoByUnsafeInfoEntity = new ArrayList<>(unsafeService.getUnsafeInfoByUnsafeInfoEntity(unsafeInfoEntity));
            if (unsafeInfoByUnsafeInfoEntity.isEmpty()) {
                return new ResultDTO(ResultDTO.SUCCESS, "未查询到隐患数据");
            }
            return new ResultDTO<>(unsafeInfoByUnsafeInfoEntity, ResultDTO.SUCCESS, "获取未处理的隐患列表成功");
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
    public ResultDTO submit(@RequestParam("id") Long id) {
        UserAuthEntity user = LoginSessionUtils.getUser();
        if (user == null) {
            return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
        }
        try {
            UnsafeInfoEntity unsafeInfoEntity = new UnsafeInfoEntity();
            unsafeInfoEntity.setId(id);
            unsafeInfoEntity.setStatus("1");
            unsafeInfoEntity.setModifyUser(user.getSafetyUser().getUsername());
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
    public ResultDTO distributed(@RequestParam("id") Long id,@RequestParam("departId") int departId) {
        UserAuthEntity user = LoginSessionUtils.getUser();
        if (user == null) {
            return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
        }
        try {
            UnsafeInfoEntity unsafeInfoEntity = new UnsafeInfoEntity();
            //分发隐患数据的ID
            unsafeInfoEntity.setId(id);
            //分发到部门的id
            unsafeInfoEntity.setDepartId(departId);
            //标记为已分发
            unsafeInfoEntity.setDistributedStatus("1");
            //分发的用户
            unsafeInfoEntity.setModifyUser(user.getSafetyUser().getUsername());
            //分发的时间
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
     * 处理隐患数据
     *
     * @param
     * @return
     */
    @PostMapping("/sign")
    public ResultDTO sign(@RequestParam("id") Long id) {
        try {
            UserAuthEntity user = LoginSessionUtils.getUser();
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
                return new ResultDTO(ResultDTO.FAILED, "更新隐患信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患信息成功");
        } catch (Exception e) {
            log.error("更新隐患信息异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患信息异常");
        }

    }

}
