package com.caogen.blog.service;

import com.caogen.blog.dto.BlogCondition;
import com.caogen.blog.entity.Blog;
import com.caogen.blog.entity.BlogType;

import java.util.List;

public interface BlogService {

    List<Blog> getBlog(BlogCondition blogCondition);

    Blog getBlog(String blogId);

    List<BlogType> getBlogType();

}
