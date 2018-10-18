package com.caogen.blog.service.impl;

import com.caogen.blog.dao.BlogDao;
import com.caogen.blog.entity.Blog;
import com.caogen.blog.entity.BlogType;
import com.caogen.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Value("${pageSize}")
    private int pageSize;

    @Override
    public List<Blog> getBlog(int currentPage, String blogType, String searchKey) {
        int offset = (currentPage - 1) * pageSize;
        return blogDao.getBlog(blogType, searchKey, offset, pageSize);
    }

    @Override
    public List<BlogType> getBlogType() {
        return blogDao.getBlogType();
    }
}
