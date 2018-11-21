package com.caogen.blog.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisCacheTest {

    @Autowired
    RedisCache redisCache;

    @Test
    public void getBlogType() throws Exception {
        redisCache.getBlogType();
    }

    @Test
    public void updateBlogType() throws Exception {
        redisCache.updateBlogType();
    }

    @Test
    public void updateBlog() throws Exception {
        redisCache.updateBlog();
    }

    @Test
    public void updateBlogById() throws Exception {
        redisCache.updateBlog("1");
    }

}