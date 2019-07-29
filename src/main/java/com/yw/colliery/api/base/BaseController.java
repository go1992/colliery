package com.yw.colliery.api.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.colliery.dto.ParamDTO;
import com.yw.colliery.entity.*;
import com.yw.colliery.sdk.utils.SpringSessionUtils;
import com.yw.colliery.service.business.MyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import static com.yw.colliery.sdk.utils.StringUtil.checkNotNull;

/**
 * 业务控制器基类
 *
 * @param <S>
 * @param <T>
 * @author xuzhou
 */
@Slf4j
public abstract class BaseController<S extends ServiceImpl<?, T>, T> {

    @Autowired
    public S service;
    @Autowired
    public MyService myService;

    /**
     * 全局异常处理
     *
     * @param e
     * @return Object
     */
    @ExceptionHandler(Exception.class)
    public Object authenticationException(Exception e) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        e.printStackTrace();
        log.error("本次接口调用异常,异常编号{}", uuid, e);
        return new ResultObject(ResultObject.FAILED, "1001", "本次接口调用异常,异常编号", uuid);
    }

    /**
     * 查询
     * @param params 查询参数
     */
    public Object commonQueryData(Map<String, Object> params) {
        log.debug("query参数:\n{}", JSON.toJSONString(params));
        //参数转换
        ParamDTO paramDTO = parseParamsDTO(params);
        //组装查询条件
        QueryWrapper<T> qw = buildQueryWrapper(paramDTO);
        //分页查询
        Page<T> page = new Page<>(paramDTO.getPageNum(), paramDTO.getPageSize());
        T parseEntity = parseEntity(params, getTClass());
        qw.allEq(true,beanToMap(parseEntity),false);

//		if(ScglScjh.class.equals(entityClass)&&"no".equals(isPage)) {
//			return service.list(qw);
//		}

        //声明结果
        Map<String, Object> map = new HashMap<>();
        //查询并分页
        IPage<T> iPage = service.page(page, qw);
        map.put("total", iPage.getTotal());
        map.put("rows", iPage.getRecords());
        return map;
    }

    /**
     * 新增
     */
    @ApiOperation(value = "新增数据", notes = "新增", response = ResultObject.class)
    @PostMapping("/save")
    public Object save(T params, @ApiParam(hidden = true) HttpServletRequest request) throws Exception {

        if (log.isDebugEnabled()) {
            log.debug("save参数:\n{}", JSON.toJSONString(params));
        }

        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        //数据所属部门 由后端从session取出用户信息 存入的值为用户的ssmk 判断T是否有ssmk字段 有自动填充值 不由前端设置
        //获取成员变量列表
        //判断是否包含ssmk字段 有自动填充值
        Field[] fields = entityClass.getDeclaredFields();
        XtgnYhlb user = (XtgnYhlb) session.getAttribute(ESessionKey.User.key);
        if (!XtgnYhlb.class.equals(entityClass) && !XtgnQyfj.class.equals(entityClass)) {
            for (Field each : fields) {
                each.setAccessible(true);
                if ("ssmk".equals(each.getName())) {
                    if (!checkNotNull(user.getSsmk())) {
                        throw new RuntimeException("用户所属部门为空,数据无法录入");
                    }
                    each.set(params, user.getSsmk());
                }
            }
        }
        //生产管理-实际产量自动填充时间
        if (ScglSjcl.class.equals(entityClass)) {
            ScglSjcl scglSjclObj = (ScglSjcl) params;
            scglSjclObj.setPcrq(new Date());
        }
        Object result = service.save(params);
        return new ResultObject(ResultObject.SUCCESS, "1003", "接口调用成功", result);
    }

    /**
     * 批量新增 (可修改为批量新增或修改 saveOrUpdateByBatch 但是前端必须在生成Excel时带上id 用户编辑excel后导入 前端转为数组的json字符串发给后端)
     */
    @ApiOperation(value = "批量新增据数据", notes = "批量新增", response = ResultObject.class)
    @PostMapping("/saveBatch")
    public Object saveBatch(String params) {
        if (log.isDebugEnabled()) log.debug("save参数:\n{}", params);
        @SuppressWarnings("unchecked")
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        List<T> entities = JSON.parseArray(params, entityClass);
        Object result = service.saveBatch(entities);
        return new ResultObject(ResultObject.SUCCESS, "1006", "接口调用成功", result);
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改数据", notes = "修改", response = ResultObject.class)
    @PostMapping("/updateById")
    public Object updateById(T params) {
        log.debug("updateById参数:\n{}", JSON.toJSONString(params));
        Object result = service.updateById(params);
        return new ResultObject(ResultObject.SUCCESS, "1004", "接口调用成功", result);
    }

    /**
     * 批量修改
     */
    @ApiOperation(value = "批量修改数据", notes = "批量修改", response = ResultObject.class)
    @PostMapping("/updateBatchById")
    public Object updateBatchById(String params) {
        log.debug("updateById参数:\n{}", JSON.toJSONString(params));
        Class<T> entityClass = getTClass();
        List<T> entities = JSON.parseArray(params, entityClass);
        Object result = service.updateBatchById(entities);
        return new ResultObject(ResultObject.SUCCESS, "1007", "接口调用成功", result);
    }

    /**
     * 删除
     */
    @ApiOperation(value = "根据id删除数据", response = ResultObject.class)
    @PostMapping("/removeByIds")
    public Object removeByIds(String params) {
        log.debug("removeByIds参数:\n{}", params);
        Object result = service.removeByIds(Arrays.asList(params.split(",")));
        return new ResultObject(ResultObject.SUCCESS, "1005", "接口调用成功", result);
    }

    /**
     * 根据传入字段进行count统计(针对非数字类型字段)
     */
    @ApiOperation(value = "单独统计", response = ResultObject.class)
    @PostMapping("/countBy")
    public Object countBy(@ApiParam(hidden = true) @RequestParam Map<String, Object> params, @ApiParam(hidden = true) HttpServletRequest request) {
        log.debug("countBy参数:\n{}", params);
        HttpSession session = request.getSession();
        Object result = null;
        @SuppressWarnings("unchecked")
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

        //用户区域部门列表sql ('数据所属煤矿','所属煤矿')
        String deptsStr = (String) session.getAttribute(ESessionKey.DeptsIdStr.key);

        //培训管理统计
        if (PxglSjlb.class.equals(entityClass)) {
            result = myService.countByPxglSjlb(params, deptsStr);
        }
        //生产管理 实际产量 按选中的年 各月分组统计
        if (ScglSjcl.class.equals(entityClass)) {
            result = myService.countByScglSjcl(params, deptsStr);
        }


        return new ResultObject(ResultObject.SUCCESS, "1005", "接口调用成功", result);
    }

    private ParamDTO parseParamsDTO(Map<String, Object> params) {
        JSONObject paramJson = JSON.parseObject(JSON.toJSONString(params));
        return JSONObject.toJavaObject(paramJson, ParamDTO.class);
    }

    private T parseEntity(Map<String,Object> params,Class<T> clazz){
        JSONObject paramJson = JSON.parseObject(JSON.toJSONString(params));
        return JSONObject.toJavaObject(paramJson, clazz);
    }

    @SuppressWarnings("unchecked")
    private Map<String,Object> beanToMap(T entity){
        JSONObject paramJson = JSONObject.parseObject(JSON.toJSONString(entity));
        return JSONObject.toJavaObject(paramJson, Map.class);
    }

    @SuppressWarnings("unchecked")
    private Class<T> getTClass(){
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
    private QueryWrapper<T> buildQueryWrapper(ParamDTO paramDTO) {
        HttpSession session = SpringSessionUtils.getRequest().getSession();
        QueryWrapper<T> qw = new QueryWrapper<>();
        //日期筛选
        if (checkNotNull(paramDTO.getStartTime(), paramDTO.getEndTime())) {
            qw.between("pcrq", paramDTO.getStartTime(), paramDTO.getEndTime());
        }
        if (StringUtils.isNotEmpty(paramDTO.getDatetype())) {
            qw.eq("datetype", paramDTO.getDatetype());
        }
        //id 查找
        if (StringUtils.isNotEmpty(paramDTO.getId())) {
            qw.eq("id", Long.valueOf(paramDTO.getId()));
        }
        //排序
        if (checkNotNull(paramDTO.getOrder(), paramDTO.getOrderName())) {
            qw.orderBy(true, "asc".equals(paramDTO.getOrder()), paramDTO.getOrderName());
        }
        //搜索
        if (checkNotNull(paramDTO.getFields(), paramDTO.getSearchParam())) {
            String[] fieldsArr = paramDTO.getFields().split(",");
            qw.and((wrapper) -> {
                for (String each : fieldsArr) {
                    wrapper.or().like(true, each, paramDTO.getSearchParam());
                }
                return wrapper;
            });

        }
//        //区域||部门过滤
//        @SuppressWarnings("unchecked")
//        List<String> departsId = (List<String>) session.getAttribute(ESessionKey.DeptsIds.key);
//        qw.in(true, "ssmk", departsId);
        return qw;
    }
}