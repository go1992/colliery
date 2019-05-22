package com.yw.colliery.service.depart;

import com.yw.colliery.entity.depart.DepartmentEntity;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.config.BaseParam;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 部门接口
 * @Date 2019-04-30
 **/
public interface DepartmentService {
    /**
     * 添加部门
     * @param entity
     * @return
     */
    int addDepart(DepartmentEntity entity);

    /**
     * 修改部门
     * @param entity
     * @return
     */
    int updateDepart(DepartmentEntity entity);

    /**
     * 删除部门
     * @param departId
     * @return
     */
    int deleteDepart(Integer departId);

    /**
     * 根据id查询部门
     * @param departId
     * @return
     */
    DepartmentEntity selectById(Integer departId);

    /**
     * 查询所有部门
     * @return
     */
    List<DepartmentEntity> selectAll();

    /**
     * 分页查询部门
     * @return
     */
    PageBean<DepartmentEntity> selectByPage(BaseParam param);

    int deleteDepartByIds(List<Integer> departIds);
}
