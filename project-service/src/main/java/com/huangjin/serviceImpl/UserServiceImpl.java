package com.huangjin.serviceImpl;

import com.huangjin.dao.UserMapper;
import com.huangjin.domain.User;
import com.huangjin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl() {
    }

    public List<User> selectList(User user) {
        this.logger.info("查询用户");
        return this.userMapper.selectList(user);
    }
}