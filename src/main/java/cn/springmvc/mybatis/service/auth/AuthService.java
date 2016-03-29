package cn.springmvc.mybatis.service.auth;

import java.util.List;

import cn.springmvc.mybatis.entity.auth.User;

public interface AuthService {

    /**
     * 根据用户名查询用户
     *
     * @param username
     *            用户名
     * @return user 用户
     */
    public User findUserByName(String username);

}
