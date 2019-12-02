package com.ax.dao.impl;

import com.ax.dao.UserMapper;
import com.ax.model.User;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserMapperImpl implements UserMapper {
    private SqlSession sqlSession;

    public UserMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<User> getAllUser() {
        return this.sqlSession.getMapper(UserMapper.class).getAllUser();
    }

    @Override
    public List<User> getUserById(String id) {
        return this.sqlSession.getMapper(UserMapper.class).getUserById(id);
    }
}
