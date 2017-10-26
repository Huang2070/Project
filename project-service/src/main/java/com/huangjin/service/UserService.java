package com.huangjin.service;

import com.huangjin.domain.User;

import java.util.List;

public interface UserService {
    List<User> selectList(User user);
}