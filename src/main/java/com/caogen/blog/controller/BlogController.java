package com.caogen.blog.controller;

import com.caogen.blog.dto.InfoResult;
import com.caogen.blog.entity.Blog;
import com.caogen.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BlogService blogService;

    @GetMapping
    public InfoResult getBlog() {
        InfoResult result = null;
        try {
            List<Blog> blogList = blogService.getBlog();
            result = new InfoResult(200, blogList);
        } catch (Exception e) {
            result = new InfoResult(500, "服务器故障！");
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            return result;
        }
    }

}
