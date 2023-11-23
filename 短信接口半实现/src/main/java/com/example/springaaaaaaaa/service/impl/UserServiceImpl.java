package com.example.springaaaaaaaa.service.impl;

import com.example.springaaaaaaaa.mapper.UserMapper;
import com.example.springaaaaaaaa.pojo.User;
import com.example.springaaaaaaaa.service.UserService;
import com.example.springaaaaaaaa.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Package: com.example.springaaaaaaaa.service.impl
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u=userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String= Md5Util.getMD5String(password);
        userMapper.add(username,md5String);
    }
}
