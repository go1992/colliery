package com.yw.colliery.api.business.geological;

import java.util.*;

import com.yw.colliery.sdk.constans.ESessionKey;
import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.entity.*;
import com.yw.colliery.sdk.aop.auth.AuthModule;
import com.yw.colliery.sdk.constans.AuthConstant;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import com.yw.colliery.api.base.BaseController;
import com.yw.colliery.service.geological.impl.BoreholeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * <p>
 * 地测信息管理-水文地质钻孔排查 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-03-06
 */
@RestController
@RequestMapping("/apiv1/dc-swdz-zkpc")
@Slf4j
public class BoreholeController extends BaseController<BoreholeServiceImpl, DcSwdzZkpc> {

    /**
     * @param params
     * @param
     * @return
     */
    @PostMapping("/queryzkpc")
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE)
    public Object query(@RequestParam Map<String, Object> params) {
        params.put("zkwz", params.get("input_zkwz"));
        return super.commonQueryData(params);
    }


    @PostMapping("/save")
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE)
    public Object save(DcSwdzZkpc params, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            List<String> deptsIds = (List<String>) session.getAttribute(ESessionKey.DeptsIds.key);
            params.setSsmk(deptsIds.get(0));
            return super.commonSave(params);
        } catch (Exception e) {
            return ResultObject.buildFailResponse("保存数据异常");
        }
    }

    @PostMapping("/updateById")
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE)
    public Object updateById(DcSwdzZkpc params) {
        return super.commonUpdateById(params);
    }

    @PostMapping("/updateBatchById")
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE)
    public Object updateBatchById(String params) {
        return super.commonUpdateBatchById(params);
    }

    @PostMapping("/removeByIds")
    @AuthModule(authId = AuthConstant.Module.GEODETIC_MODULE)
    public Object removeByIds(String params) {
        return super.commonRemoveByIds(params);
    }
}
