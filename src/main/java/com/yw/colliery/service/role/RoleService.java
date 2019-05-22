package com.yw.colliery.service.role;

import com.yw.colliery.entity.role.RoleEntity;
import com.yw.colliery.sdk.config.PageBean;
import com.yw.colliery.sdk.config.BaseParam;

import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 角色接口
 * @Date 2019-04-30
 **/
public interface RoleService {
    /**
     * 添加角色
     * @param entity
     * @return
     */
    int addRole(RoleEntity entity);

    /**
     * 修改角色
     * @param entity
     * @return
     */
    int updateRole(RoleEntity entity);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    int deleteRole(Integer roleId);

    /**
     * 根据id查询角色
     * @param roleId
     * @return
     */
    RoleEntity selectById(Integer roleId);

    /**
     * 查询所有角色
     * @return
     */
    List<RoleEntity> selectAll();

    /**
     * 分页查询角色
     * @return
     */
    PageBean<RoleEntity> selectByPage(BaseParam param);

    int deleteRoleByIds(List<Integer> roleIds);
}
