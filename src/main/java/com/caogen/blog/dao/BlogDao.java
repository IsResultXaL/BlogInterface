package com.caogen.blog.dao;

import com.caogen.blog.dto.BlogCondition;
import com.caogen.blog.entity.Blog;
import com.caogen.blog.entity.BlogType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {

    String[] getBlogId(BlogCondition blogCondition);

    List<BlogType> getBlogType();

    List<Blog> getBlog();

    Blog getBlogById(@Param("blogId") String blogId);

}
