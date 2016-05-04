package com.slin.dao;

import com.slin.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author SongLin.Yang
 * @data 2016-05-04 10:12
 */
@Repository
public interface UserMapper {

    public User getUser(int id);
}
