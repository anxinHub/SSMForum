package com.ax.dao;

import com.ax.model.User;

import java.util.List;

public interface UserMapper {
    public List<User> getAllUser();
    public List<User> getUserById(String id);
}
