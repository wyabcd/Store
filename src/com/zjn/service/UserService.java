package com.zjn.service;

import com.zjn.domain.User;

public interface UserService {
    /**
     * 注册用户
     * @param user 封装了用户数据的userbean
     */
    void regist(User user);

    /**
     * 激活用户的方法
     * @param activecode 激活码
     */
    void acitveUser(String activecode);

    /**
     * 根据用户名密码查找用户
     * @param username
     * @param password
     */
    User getUserByNameAndPsw(String username, String password);
}
