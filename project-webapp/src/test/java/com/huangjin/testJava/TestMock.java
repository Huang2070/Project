package com.huangjin.testJava;

import com.huangjin.dao.UserMapper;
import com.huangjin.domain.User;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2016-9-28.
 */
public class TestMock {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(1);

        List<User> expectUser = new ArrayList<>();

        UserMapper mock = EasyMock.createMock(UserMapper.class);

        EasyMock.expect(mock.selectList(user1)).andReturn(expectUser);
        EasyMock.replay(mock);

        System.out.println(expectUser);
    }
}
