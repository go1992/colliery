package com.yw.colliery.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author: xuzhou-013
 * @Date: 2019/7/26 14:45
 * @Description: 业务数据操作父接口
 */
public interface BaseBusinessService<T> extends IService<T> {

    Boolean saveData(T entity);

    Boolean updateData();
}
