package com.yw.colliery.api.unsafe;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.unsafe.UnsafeInfoEntity;
import com.yw.colliery.entity.unsafe.UnsafePlanEntity;
import com.yw.colliery.entity.user.UserRelationEntity;
import com.yw.colliery.sdk.aop.AuthModule;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.LoginSessionUtils;
import com.yw.colliery.service.unsafe.UnsafePlanService;
import com.yw.colliery.service.unsafe.UnsafeService;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/apiv1/unsafe")
@Slf4j
public class UnsafeController implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UnsafeService unsafeService;

    private static final ConcurrentHashMap<String, String> COLUMN_NAME_MAP = new ConcurrentHashMap<>();


    /**
     * 隐患数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
    public ResultDTO input(@RequestBody String data) {
        try {
//            UserRelationEntity user = LoginSessionUtils.getUser();
//            if (user == null) {
//                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
//            }
            UnsafeInfoEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafeInfoEntity.class);
//            unsafeInfoEntity.setCreateUser(user.getSafetyUser().getUsername());
            unsafeInfoEntity.setCreateUser("xz");
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
//    @AuthModule(authId = {AuthConstant.Module.UNSAFE_MODULE_WATCH, AuthConstant.Module.UNSAFE_MODULE_SUPER})
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
//    @AuthModule(authId = {AuthConstant.Module.UNSAFE_MODULE_WATCH, AuthConstant.Module.UNSAFE_MODULE_SUPER})
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
     * 获取某部门的隐患数据
     *
     * @param
     * @return
     */
    @PostMapping("/get/unsafeInfo/condition")
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            UnsafeInfoEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafeInfoEntity.class);
            //列名转换一下
            if (unsafeInfoEntity.getOrderName() != null && COLUMN_NAME_MAP.containsKey(unsafeInfoEntity.getOrderName())) {
                unsafeInfoEntity.setOrderName(COLUMN_NAME_MAP.get(unsafeInfoEntity.getOrderName()));
            }
            PageBean<UnsafeInfoEntity> unsafeInfoByUnsafeInfoEntity = unsafeService.getUnsafeInfoByUnsafeInfoEntity(unsafeInfoEntity
                    , Optional.ofNullable(unsafeInfoEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(unsafeInfoEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            if (unsafeInfoByUnsafeInfoEntity.getList().isEmpty()) {
                return new ResultDTO(ResultDTO.SUCCESS, "未查询到隐患数据");
            }
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
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
    public ResultDTO submit(@RequestParam("id") Long id) {
//        UserRelationEntity user = LoginSessionUtils.getUser();
//        if (user == null) {
//            return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
//        }
        try {
            UnsafeInfoEntity unsafeInfoEntity = new UnsafeInfoEntity();
            unsafeInfoEntity.setId(id);
            unsafeInfoEntity.setStatus("1");
//            unsafeInfoEntity.setModifyUser(user.getSafetyUser().getUsername());
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
//    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE_SUPER)
    public ResultDTO distributed(@RequestParam("id") Long id, @RequestParam("departId") int departId) {
//        UserRelationEntity user = LoginSessionUtils.getUser();
//        if (user == null) {
//            return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
//        }
        try {
            UnsafeInfoEntity unsafeInfoEntity = new UnsafeInfoEntity();
            //分发隐患数据的ID
            unsafeInfoEntity.setId(id);
            //分发到部门的id
            unsafeInfoEntity.setDepartId(departId);
            //标记为已分发
            unsafeInfoEntity.setDistributedStatus("1");
            //分发的用户
//            unsafeInfoEntity.setModifyUser(user.getSafetyUser().getUsername());
            unsafeInfoEntity.setModifyUser("xz");
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
//            UserRelationEntity user = LoginSessionUtils.getUser();
//            if (user == null) {
//                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
//            }
            UnsafeInfoEntity unsafeInfoEntity = new UnsafeInfoEntity();
            unsafeInfoEntity.setId(id);
            unsafeInfoEntity.setSignStatus("1");
//            unsafeInfoEntity.setModifyUser(user.getSafetyUser().getUsername());
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
     * 删除隐患计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    public ResultDTO delete(@RequestBody String data) {
        try {
//            UserAuthEntity user = LoginSessionUtils.getUser();
//            if (user == null) {
//                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
//            }
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
     * 更新隐患计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    public ResultDTO submit(@RequestBody String data) {
        try {
//            UserAuthEntity user = LoginSessionUtils.getUser();
//            if (user == null) {
//                return new ResultDTO(ResultDTO.FAILED, "登陆已过期，请重新登陆");
//            }
            UnsafeInfoEntity unsafeInfoEntity = JSONObject.toJavaObject(JSON.parseObject(data), UnsafeInfoEntity.class);
//            unsafeInfoEntity.setModifyUser(user.getSafetyUser().getUsername());
            unsafeInfoEntity.setModifyUser("xz");
            unsafeInfoEntity.setModifyDate(new Date());
            Integer integer = unsafeService.upateUnsafeInfo(unsafeInfoEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新隐患排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新隐患排查计划信息成功");
        } catch (Exception e) {
            log.error("更新隐患排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新隐患排查计划信息异常");
        }

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        COLUMN_NAME_MAP.put("departId", "depart_id");
        COLUMN_NAME_MAP.put("plan_content", "content");
        COLUMN_NAME_MAP.put("plan_name", "planName");
        COLUMN_NAME_MAP.put("distributedStatus", "distributed_status");
        COLUMN_NAME_MAP.put("signStatus", "sign_status");
        COLUMN_NAME_MAP.put("coalMine", "coal_mine");
        COLUMN_NAME_MAP.put("checkPerson", "check_person");
        COLUMN_NAME_MAP.put("checkType", "check_type");
        COLUMN_NAME_MAP.put("checkTime", "check_time");
        COLUMN_NAME_MAP.put("reformDate", "reform_date");
        COLUMN_NAME_MAP.put("dutyDepart", "duty_depart");
        COLUMN_NAME_MAP.put("dutyPerson", "duty_person");
        COLUMN_NAME_MAP.put("fineMoney", "fine_money");
        COLUMN_NAME_MAP.put("fineFind", "fine_find");
    }

}
