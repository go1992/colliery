package com.yw.colliery.api.business.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.productmanager.RawCoalEntity;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.utils.StringUtil;
import com.yw.colliery.service.product.impl.RawCoalServiceImpl;
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
 * @Deprecated 原煤产量数据记录
 */
@RestController
@RequestMapping("/apiv1/raw/coal")
@Slf4j
public class RawCoalController {

    @Autowired
    private RawCoalServiceImpl rawCoalService;


    /**
     * 原煤产量台账数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    public ResultDTO input(@RequestBody String data) {
        try {
            RawCoalEntity rawCoalEntity = JSONObject.toJavaObject(JSON.parseObject(data), RawCoalEntity.class);
            Integer integer = rawCoalService.insert(rawCoalEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化原煤产量台账数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化原煤产量台账数据成功");
        } catch (Exception e) {
            log.error("保存原煤产量台账数据异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "保存原煤产量台账数据异常");
        }

    }

    /**
     * 获取某部门的原煤产量台账数据
     *
     * @param
     * @return
     */
    @PostMapping("/get")
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            RawCoalEntity rawCoalEntity = JSONObject.toJavaObject(JSON.parseObject(data), RawCoalEntity.class);
            //列名转换一下
            if (rawCoalEntity.getOrderName() != null) {
                rawCoalEntity.setOrderName(StringUtil.camelToUnderline(rawCoalEntity.getOrderName()));
            }
            PageBean<RawCoalEntity> unsafeInfoByUnsafeInfoEntity = rawCoalService.getByCondition(rawCoalEntity
                    , Optional.ofNullable(rawCoalEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(rawCoalEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafeInfoByUnsafeInfoEntity.getTotal());
            resultMap.put("rows", unsafeInfoByUnsafeInfoEntity.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取原煤产量台账列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取原煤产量台账列表异常");
        }


    }


    /**
     * 删除原煤产量台账计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    public ResultDTO delete(@RequestBody String data) {
        try {
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = rawCoalService.delete(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除原煤产量台账信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除原煤产量台账信息成功");
        } catch (Exception e) {
            log.error("删除原煤产量台账信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除原煤产量台账排信息异常");
        }

    }

    /**
     * 更新原煤产量台账计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    public ResultDTO submit(@RequestBody String data) {
        try {
            RawCoalEntity rawCoalEntity = JSONObject.toJavaObject(JSON.parseObject(data), RawCoalEntity.class);
            Integer integer = rawCoalService.update(rawCoalEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新原煤产量台账排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新原煤产量台账排查计划信息成功");
        } catch (Exception e) {
            log.error("更新原煤产量台账排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新原煤产量台账排查计划信息异常");
        }

    }

}
