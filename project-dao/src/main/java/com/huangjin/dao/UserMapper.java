package com.huangjin.dao;

import com.huangjin.domain.User;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer var1);

    int insert(User var1);

    int insertSelective(User var1);

    User selectByPrimaryKey(Integer var1);

    int updateByPrimaryKeySelective(User var1);

    int updateByPrimaryKey(User var1);

    List<User> selectList(User var1);
}
