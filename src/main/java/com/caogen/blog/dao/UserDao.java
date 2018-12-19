package com.caogen.blog.dao;

import com.caogen.blog.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUserByUserName(@Param("userName") String userName);

    void addUser(User user);

}
