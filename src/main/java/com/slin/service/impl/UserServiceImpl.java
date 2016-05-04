package com.slin.service.impl;

import com.slin.dao.UserMapper;
import com.slin.pojo.User;
import com.slin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author SongLin.Yang
 * @data 2016-05-04 10:11
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public User getUser(int id) {
        return userMapper.getUser(id);
    }
}
