package com.yw.colliery.api.business.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.productmanager.KeyProjectEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.StringUtil;
import com.yw.colliery.service.product.impl.KeyProjectServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * @author xuzhou
 * @Deprecated 重点工程进度
 */
@RestController
@RequestMapping("/apiv1/key/project")
@Slf4j
public class KeyProjectController {

    @Autowired
    private KeyProjectServiceImpl keyProjectService;

    /**
     * 重点工程进度数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE)
    public ResultDTO input(@RequestBody String data) {
        try {
            KeyProjectEntity keyProjectEntity = JSONObject.toJavaObject(JSON.parseObject(data), KeyProjectEntity.class);
            Integer integer = keyProjectService.insert(keyProjectEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化重点工程进度数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化重点工程进度数据成功");
        } catch (Exception e) {
            log.error("保存重点工程进度数据异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "保存重点工程进度数据异常");
        }

    }

    /**
     * 获取某部门的重点工程进度数据
     *
     * @param
     * @return
     */
    @PostMapping("/get")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE, level = AuthConstant.Level.LOW)
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            KeyProjectEntity keyProjectEntity = JSONObject.toJavaObject(JSON.parseObject(data), KeyProjectEntity.class);
            //列名转换一下
            if (keyProjectEntity.getOrderName() != null) {
                keyProjectEntity.setOrderName(StringUtil.camelToUnderline(keyProjectEntity.getOrderName()));
            }
            PageBean<KeyProjectEntity> unsafeInfoByUnsafeInfoEntity = keyProjectService.getByCondition(keyProjectEntity
                    , Optional.ofNullable(keyProjectEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(keyProjectEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafeInfoByUnsafeInfoEntity.getTotal());
            resultMap.put("rows", unsafeInfoByUnsafeInfoEntity.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取重点工程进度列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取重点工程进度列表异常");
        }


    }


    /**
     * 删除重点工程进度计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE)
    public ResultDTO delete(@RequestBody String data) {
        try {
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = keyProjectService.delete(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除重点工程进度信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除重点工程进度信息成功");
        } catch (Exception e) {
            log.error("删除重点工程进度信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除重点工程进度排信息异常");
        }

    }

    /**
     * 更新重点工程进度计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE)
    public ResultDTO submit(@RequestBody String data) {
        try {
            KeyProjectEntity keyProjectEntity = JSONObject.toJavaObject(JSON.parseObject(data), KeyProjectEntity.class);
            Integer integer = keyProjectService.update(keyProjectEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新重点工程进度排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新重点工程进度排查计划信息成功");
        } catch (Exception e) {
            log.error("更新重点工程进度排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新重点工程进度排查计划信息异常");
        }

    }
}
