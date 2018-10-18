package com.caogen.blog.controller;

import com.caogen.blog.dto.InfoResult;
import com.caogen.blog.entity.Blog;
import com.caogen.blog.entity.BlogType;
import com.caogen.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * blog interface
 *
 * @author kcaogen
 */
@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BlogService blogService;

    @GetMapping
    public InfoResult getBlog(@RequestParam(value = "currentPage", defaultValue = "1", required = false) int currentPage,
                              @RequestParam(value = "blogType", defaultValue = "", required = false) String blogType,
                              @RequestParam(value = "searchKey", defaultValue = "", required = false) String searchKey) {
        InfoResult result = new InfoResult(HttpServletResponse.SC_NO_CONTENT, null);
        try {
            List<Blog> blogList = blogService.getBlog(currentPage, blogType, searchKey);
            if (blogList != null && !blogList.isEmpty()) {
                result = new InfoResult(HttpServletResponse.SC_OK, blogList);
            }
        } catch (Exception e) {
            result = new InfoResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器故障！");
            logger.error("getBlog: " + e);
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/type")
    public InfoResult getBlogType() {
        InfoResult result = new InfoResult(HttpServletResponse.SC_NO_CONTENT, null);
        try {
            List<BlogType> typeList = blogService.getBlogType();
            if (typeList != null && !typeList.isEmpty()) {
                result = new InfoResult(HttpServletResponse.SC_OK, typeList);
            }
        } catch (Exception e) {
            result = new InfoResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器故障！");
            logger.error("getBlogType: " + e);
            e.printStackTrace();
        }
        return result;
    }

}
