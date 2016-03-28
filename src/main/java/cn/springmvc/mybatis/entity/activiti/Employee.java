package cn.springmvc.mybatis.entity.activiti;

import cn.springmvc.mybatis.entity.BaseEntity;

/**
 * 用户表
 */
public class Employee implements BaseEntity<String> {

    private static final long serialVersionUID = -1878993594232768170L;

    private String id;
    private String name;// 用户名
    private String password;// 密码
    private String email;// 电子邮箱
    private String role;// 角色
    private String managerId;// 管理员用户ID

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
