package com.caogen.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {

    private Integer blogId;

    private String blogName;

    private String blogImg;

    private String introduction;

    private String content;

    private Date time;

    private Integer browse;

    private Integer praise;

    private String blogType;

    private String reprintedUrl;

}
