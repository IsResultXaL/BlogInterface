package com.caogen.blog.dao;

import com.caogen.blog.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    public void getUserByUserName() throws Exception {
        User user = userDao.getUserByUserName("kcaogen@163.com");
        System.out.println(user);
    }

}