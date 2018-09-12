package com.caogen.blog.dao;

import com.caogen.blog.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {

    List<Blog> getBlog();

}
