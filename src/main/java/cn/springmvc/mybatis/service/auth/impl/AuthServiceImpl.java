package cn.springmvc.mybatis.service.auth.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.springmvc.mybatis.entity.auth.User;
import cn.springmvc.mybatis.mapper.auth.UserMapper;
import cn.springmvc.mybatis.service.auth.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

}
