package com.yw.colliery.api.base;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.colliery.dto.ParamDTO;
import com.yw.colliery.dto.ResultObject;
import com.yw.colliery.entity.*;
import com.yw.colliery.sdk.constans.ESessionKey;
import com.yw.colliery.sdk.utils.BeanUtils;
import com.yw.colliery.sdk.utils.SpringSessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    protected Object commonQueryData(Map<String, Object> params) {
        log.debug("query参数:\n{}", JSON.toJSONString(params));
        //参数转换
        ParamDTO paramDTO = BeanUtils.mapToBean(params, ParamDTO.class);
        //组装查询条件
        QueryWrapper<T> qw = buildQueryWrapper(paramDTO);
        //分页查询
        Page<T> page = new Page<>(paramDTO.getPageNum(), paramDTO.getPageSize());
        //字段条件查询
        T parseEntity = BeanUtils.mapToBean(params, getTClass());
        qw.allEq(true,BeanUtils.beanToMap(parseEntity),false);
        //组装结果数据
        Map<String, Object> map = new HashMap<>(4);
        //查询并分页
        IPage<T> iPage = service.page(page, qw);
        map.put("total", iPage.getTotal());
        map.put("rows", iPage.getRecords());
        return map;
    }

    /**
     * 数据保存
     * @param params 数据
     */
    protected Object commonSave(T params) throws Exception {

        log.debug("save参数:\n{}", JSON.toJSONString(params));
        HttpSession session = SpringSessionUtils.getRequest().getSession();

        Class<T> entityClass = getTClass();

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
        Object result = service.save(params);
        return new ResultObject(ResultObject.SUCCESS, "1003", "接口调用成功", result);
    }

    /**
     * 修改
     * @param params 更新参数
     *
     */
    protected Object commonUpdateById(T params) {
        log.debug("updateById参数:\n{}", JSON.toJSONString(params));
        Object result = service.updateById(params);
        return new ResultObject(ResultObject.SUCCESS, "1004", "接口调用成功", result);
    }

    /**
     * 批量修改
     */
    protected Object commonUpdateBatchById(String params) {
        log.debug("updateById参数:\n{}", JSON.toJSONString(params));
        Class<T> entityClass = getTClass();
        List<T> entities = JSON.parseArray(params, entityClass);
        Object result = service.updateBatchById(entities);
        return new ResultObject(ResultObject.SUCCESS, "1007", "接口调用成功", result);
    }

    /**
     * 删除
     */
    protected Object commonRemoveByIds(String params) {
        log.debug("removeByIds参数:\n{}", params);
        Object result = service.removeByIds(Arrays.asList(params.split(",")));
        return new ResultObject(ResultObject.SUCCESS, "1005", "接口调用成功", result);
    }


    private QueryWrapper<T> buildQueryWrapper(ParamDTO paramDTO) {
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
        return qw;
    }

    /**
     * 获取泛型类型
     * @return
     */
    @SuppressWarnings("unchecked")
    private Class<T> getTClass(){
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
}