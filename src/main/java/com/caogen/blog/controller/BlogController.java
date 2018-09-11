package com.caogen.blog.controller;

import com.caogen.blog.dto.InfoResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/test")
    public InfoResult test() {
        InfoResult infoResult = new InfoResult(200, "Hello CaoGen!");
        return infoResult;
    }

}
