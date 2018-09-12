package com.caogen.blog.service.impl;

import com.caogen.blog.dao.BlogDao;
import com.caogen.blog.entity.Blog;
import com.caogen.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Override
    public List<Blog> getBlog() {
        return blogDao.getBlog();
    }
}
