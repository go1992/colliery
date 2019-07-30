package com.yw.colliery.api.business.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.productmanager.HandOverEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.StringUtil;
import com.yw.colliery.service.product.impl.HandOverServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


/**
 * @author xuzhou
 * @Deprecated 调度员交接班记录
 */
@RestController
@RequestMapping("/apiv1/handover")
@Slf4j
public class HandOverController {

    @Autowired
    private HandOverServiceImpl handOverService;


    /**
     * 调度员交接班数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    @AuthModule(authId = AuthConstant.Module.UNSAFE_MODULE, level = AuthConstant.Level.HIGH)
    public ResultDTO input(@RequestBody String data) {
        try {
            HandOverEntity handOverEntity = JSONObject.toJavaObject(JSON.parseObject(data), HandOverEntity.class);
            Integer integer = handOverService.insert(handOverEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化调度员交接班数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化调度员交接班数据成功");
        } catch (Exception e) {
            log.error("保存调度员交接班数据异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "保存调度员交接班数据异常");
        }

    }

    /**
     * 获取某部门的调度员交接班数据
     *
     * @param
     * @return
     */
    @PostMapping("/get")
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            HandOverEntity handOverEntity = JSONObject.toJavaObject(JSON.parseObject(data), HandOverEntity.class);
            //列名转换一下
            if (handOverEntity.getOrderName() != null) {
                handOverEntity.setOrderName(StringUtil.camelToUnderline(handOverEntity.getOrderName()));
            }
            PageBean<HandOverEntity> unsafeInfoByUnsafeInfoEntity = handOverService.getByCondition(handOverEntity
                    , Optional.ofNullable(handOverEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(handOverEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafeInfoByUnsafeInfoEntity.getTotal());
            resultMap.put("rows", unsafeInfoByUnsafeInfoEntity.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取调度员交接班列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取调度员交接班列表异常");
        }


    }


    /**
     * 删除调度员交接班计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    public ResultDTO delete(@RequestBody String data) {
        try {
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = handOverService.delete(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除调度员交接班信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除调度员交接班信息成功");
        } catch (Exception e) {
            log.error("删除调度员交接班信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除调度员交接班排信息异常");
        }

    }

    /**
     * 更新调度员交接班计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    public ResultDTO submit(@RequestBody String data) {
        try {
            HandOverEntity handOverEntity = JSONObject.toJavaObject(JSON.parseObject(data), HandOverEntity.class);
            Integer integer = handOverService.update(handOverEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新调度员交接班排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新调度员交接班排查计划信息成功");
        } catch (Exception e) {
            log.error("更新调度员交接班排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新调度员交接班排查计划信息异常");
        }

    }

}
