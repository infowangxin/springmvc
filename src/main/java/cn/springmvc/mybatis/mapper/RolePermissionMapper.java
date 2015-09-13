package cn.springmvc.mybatis.mapper;

import cn.springmvc.mybatis.entity.RolePermission;

public interface RolePermissionMapper extends BaseMapper<String, RolePermission> {

    public RolePermission findRolePermission(RolePermission per);

}
