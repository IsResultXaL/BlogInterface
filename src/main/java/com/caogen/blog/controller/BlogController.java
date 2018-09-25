package com.caogen.blog.controller;

import com.caogen.blog.dto.InfoResult;
import com.caogen.blog.entity.Blog;
import com.caogen.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * blog interface
 * @author kcaogen
 */
@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BlogService blogService;

    @GetMapping("/{currentPage}")
    public InfoResult getBlog(@PathVariable("currentPage") int currentPage) {
        InfoResult result = new InfoResult(HttpServletResponse.SC_NO_CONTENT, null);
        try {
            List<Blog> blogList = blogService.getBlog();
            if (blogList!=null && !blogList.isEmpty()) {
                result = new InfoResult(HttpServletResponse.SC_OK, blogList);
            }
        } catch (Exception e) {
            result = new InfoResult(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器故障！");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
