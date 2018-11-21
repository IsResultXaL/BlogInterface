package com.caogen.blog.entity;

import java.util.Date;

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

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogImg() {
        return blogImg;
    }

    public void setBlogImg(String blogImg) {
        this.blogImg = blogImg;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public String getBlogType() {
        return blogType;
    }

    public void setBlogType(String blogType) {
        this.blogType = blogType;
    }

    public String getReprintedUrl() {
        return reprintedUrl;
    }

    public void setReprintedUrl(String reprintedUrl) {
        this.reprintedUrl = reprintedUrl;
    }
}
