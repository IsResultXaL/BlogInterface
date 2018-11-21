package com.caogen.blog.service.impl;

import com.caogen.blog.cache.RedisCache;
import com.caogen.blog.dao.BlogDao;
import com.caogen.blog.dto.BlogCondition;
import com.caogen.blog.entity.Blog;
import com.caogen.blog.entity.BlogType;
import com.caogen.blog.service.BlogService;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Autowired
    RedisCache redisCache;

    @Value("${pageSize}")
    private int pageSize;

    @Override
    public List<Blog> getBlog(BlogCondition blogCondition) {
        if (blogCondition.getPageSize() <= 0) {
            blogCondition.setPageSize(pageSize);
        }

        int offset = (blogCondition.getCurrentPage() - 1) * blogCondition.getPageSize();
        blogCondition.setOffset(offset);
        String[] blogIds = blogDao.getBlogId(blogCondition);

        List<Blog> blogList = redisCache.getBlog(blogIds);
        return blogList;
    }

    @Override
    public Blog getBlog(String blogId) {
        Blog blog = new Blog();
        if (Strings.isNullOrEmpty(blogId)) {
            return blog;
        }

        List<Blog> blogList = redisCache.getBlog(blogId);
        if (CollectionUtils.isEmpty(blogList)) {
            return blog;
        }

        blog = blogList.get(0);

        return blog;
    }

    @Override
    public List<BlogType> getBlogType() {
        List<BlogType> blogTypeList = redisCache.getBlogType();

        if (CollectionUtils.isEmpty(blogTypeList)) {
            redisCache.updateBlogType();
            blogTypeList = redisCache.getBlogType();
        }

        return blogTypeList;
    }
}
