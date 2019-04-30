package com.yw.colliery.mapper.role;

import com.yw.colliery.entity.role.RoleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
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
}
