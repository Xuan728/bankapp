package com.example.springaaaaaaaa.service;

import com.example.springaaaaaaaa.pojo.User;

/**
 * ClassName: UserService
 * Package: com.example.springaaaaaaaa.service
 */
public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);
}
