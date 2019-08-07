package com.yw.colliery.api.business.product;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.colliery.dto.ResultDTO;
import com.yw.colliery.entity.productmanager.EntryCollieryEntity;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.constans.AuthConstant;
import com.yw.colliery.sdk.utils.StringUtil;
import com.yw.colliery.service.product.impl.EntryCollieryServiceImpl;
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
 * @Description 调度人员入井记录
 */
@RestController
@RequestMapping("/apiv1/entry/colliery")
@Slf4j
public class EntryCollieryController {

    @Autowired
    private EntryCollieryServiceImpl entryCollieryService;

    /**
     * 调度人员入井记录数据持久化
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE)
    public ResultDTO input(@RequestBody String data) {
        try {
            EntryCollieryEntity entryCollieryEntity = JSONObject.toJavaObject(JSON.parseObject(data), EntryCollieryEntity.class);
            Integer integer = entryCollieryService.insert(entryCollieryEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "持久化调度人员入井记录数据失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "持久化调度人员入井记录数据成功");
        } catch (Exception e) {
            log.error("保存调度人员入井记录数据异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "保存调度人员入井记录数据异常");
        }

    }

    /**
     * 获取某部门的调度人员入井记录数据
     *
     * @param
     * @return
     */
    @PostMapping("/get")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE, level = AuthConstant.Level.LOW)
    public Object getUnsafeInfoByCondition(@RequestBody String data) {
        try {
            EntryCollieryEntity entryCollieryEntity = JSONObject.toJavaObject(JSON.parseObject(data), EntryCollieryEntity.class);
            //列名转换一下
            if (entryCollieryEntity.getOrderName() != null) {
                entryCollieryEntity.setOrderName(StringUtil.camelToUnderline(entryCollieryEntity.getOrderName()));
            }
            PageBean<EntryCollieryEntity> unsafeInfoByUnsafeInfoEntity = entryCollieryService.getByCondition(entryCollieryEntity
                    , Optional.ofNullable(entryCollieryEntity.getPageNum()).orElse(0)
                    , Optional.ofNullable(entryCollieryEntity.getPageSize()).orElse(0));
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("total", unsafeInfoByUnsafeInfoEntity.getTotal());
            resultMap.put("rows", unsafeInfoByUnsafeInfoEntity.getList());
            return resultMap;
        } catch (Exception e) {
            log.error("获取调度人员入井记录列表异常", e);
            return new ResultDTO(ResultDTO.SUCCESS, "获取调度人员入井记录列表异常");
        }


    }


    /**
     * 删除调度人员入井记录计划
     *
     * @param
     * @return
     */
    @PostMapping("/delete")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE)
    public ResultDTO delete(@RequestBody String data) {
        try {
            List<String> strings = JSON.parseArray(data, String.class);
            Integer integer = entryCollieryService.delete(strings);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "删除调度人员入井记录信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "删除调度人员入井记录信息成功");
        } catch (Exception e) {
            log.error("删除调度人员入井记录信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "删除调度人员入井记录排信息异常");
        }

    }

    /**
     * 更新调度人员入井记录计划
     *
     * @param
     * @return
     */
    @PostMapping("/update")
    @AuthModule(authId = AuthConstant.Module.DISPATCH_MODULE)
    public ResultDTO submit(@RequestBody String data) {
        try {
            EntryCollieryEntity EntryCollieryEntity = JSONObject.toJavaObject(JSON.parseObject(data), EntryCollieryEntity.class);
            Integer integer = entryCollieryService.update(EntryCollieryEntity);
            if (integer < 0) {
                return new ResultDTO(ResultDTO.FAILED, "更新调度人员入井记录排查计划信息失败");
            }
            return new ResultDTO(ResultDTO.SUCCESS, "更新调度人员入井记录排查计划信息成功");
        } catch (Exception e) {
            log.error("更新调度人员入井记录排查计划信息异常", e);
            return new ResultDTO(ResultDTO.FAILED, "更新调度人员入井记录排查计划信息异常");
        }

    }

}
