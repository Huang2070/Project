package com.huangjin.serviceImpl;

import java.util.List;

import com.huangjin.domain.User;
import com.huangjin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 17:36 2021-08-09
 */
@Service("user2Service")
public class User2ServiceImpl implements UserService {
    @Override
    public List<User> selectList(User user) {
        return null;
    }
}
