package com.example.springaaaaaaaa.service;

import com.example.springaaaaaaaa.pojo.User;

/**
 * ClassName: UserService
 * Package: com.example.springaaaaaaaa.service
 */
public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);

    void update(User user);

    //更新头像
    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
