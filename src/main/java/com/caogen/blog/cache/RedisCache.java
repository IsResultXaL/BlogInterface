package com.caogen.blog.cache;

import com.caogen.blog.dao.BlogDao;
import com.caogen.blog.entity.Blog;
import com.caogen.blog.entity.BlogType;
import com.caogen.blog.finals.RedisKey;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class RedisCache {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    BlogDao blogDao;

    /**
     * 更新所有博客类型
     */
    public void updateBlogType() {
        //清空博客类型数据
        if (redisTemplate.opsForList().size(RedisKey.BLOGTYPES) > 0) {
            redisTemplate.opsForList().rightPop(RedisKey.BLOGTYPES);
        }

        //存储博客类型数据到redis
        List<BlogType> blogTypeList = blogDao.getBlogType();
        redisTemplate.opsForList().leftPushAll(RedisKey.BLOGTYPES, blogTypeList);
    }

    /**
     * 获取所有博客类型
     *
     * @return
     */
    public List<BlogType> getBlogType() {
        List<BlogType> blogTypeList = redisTemplate.opsForList().range(RedisKey.BLOGTYPES, 0, -1);
        return blogTypeList;
    }


    /**
     * 更新所有博客
     */
    public void updateBlog() {
        //清空博客数据
        if (redisTemplate.opsForHash().size(RedisKey.BLOGS) > 0) {
            redisTemplate.delete(RedisKey.BLOGS);
        }

        //存储博客数据到redis
        List<Blog> blogList = blogDao.getBlog();
        if (CollectionUtils.isEmpty(blogList)) {
            return;
        }

        for (int i = 0; i < blogList.size(); i++) {
            String blogId = String.valueOf(blogList.get(i).getBlogId());
            redisTemplate.opsForHash().put(RedisKey.BLOGS, blogId, blogList.get(i));
        }
    }


    /**
     * 更新单个博客
     *
     * @param blogId
     */
    public void updateBlog(String blogId) {
        //清空这个博客数据
        if (redisTemplate.opsForHash().hasKey(RedisKey.BLOGS, blogId)) {
            redisTemplate.opsForHash().delete(RedisKey.BLOGS, blogId);
        }

        //存储当前博客到redis
        Blog blog = blogDao.getBlogById(blogId);
        redisTemplate.opsForHash().put(RedisKey.BLOGS, blogId, blog);
    }

    /**
     * 根据博客ID获取博客数据
     *
     * @param blogIds
     * @return
     */
    public List<Blog> getBlog(String... blogIds) {
        List<Blog> blogList = Lists.newArrayList();

        if (blogIds == null || blogIds.length <= 0) {
            return blogList;
        }

        for (int i = 0; i < blogIds.length; i++) {
            String blogId = blogIds[i];
            if (!redisTemplate.opsForHash().hasKey(RedisKey.BLOGS, blogId)) {
                Blog blog = blogDao.getBlogById(blogId);
                redisTemplate.opsForHash().put(RedisKey.BLOGS, blogId, blog);
            }

            Blog blog = (Blog) redisTemplate.opsForHash().get(RedisKey.BLOGS, blogId);
            blogList.add(blog);
        }

        return blogList;
    }

}
