package com.yw.colliery.service.product;


import com.yw.colliery.sdk.config.PageBean;

import java.util.List;

public interface BaseProductService<T> {

    /**
     * 隐患排查计划数据持久化
     * @param entity
     * @return
     */
    Integer insert(T entity);

    /**
     * 条件查询
     * @param entity
     * @return
     */
    PageBean getByCondition(T entity, int pagNum, int pagSize);


    /**
     * 更新数据
     * @param entity
     * @return
     */
    Integer update(T entity);

    /**
     * 删除数据
     * @param idList
     * @return
     */
    Integer delete(List<String> idList);

}
