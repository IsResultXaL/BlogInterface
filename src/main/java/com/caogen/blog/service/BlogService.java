package com.caogen.blog.service;

import com.caogen.blog.entity.Blog;
import com.caogen.blog.entity.BlogType;

import java.util.List;

public interface BlogService {

    List<Blog> getBlog(int currentPage, String blogType, String searchKey);

    List<BlogType> getBlogType();

}
