package com.caogen.blog.dao;

import com.caogen.blog.entity.Blog;
import com.caogen.blog.entity.BlogType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {

    List<Blog> getBlog(@Param("blogType") String blogType,
                       @Param("searchKey") String searchKey,
                       @Param("offset") int offset,
                       @Param("pageSize") int pageSize);

    List<BlogType> getBlogType();

}
