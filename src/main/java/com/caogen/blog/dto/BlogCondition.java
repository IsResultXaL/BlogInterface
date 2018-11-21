package com.caogen.blog.dto;

import io.swagger.annotations.ApiModelProperty;

public class BlogCondition {

    private String blogType;

    private String searchKey;

    private int currentPage;

    @ApiModelProperty(hidden = true)
    private int offset;

    private int pageSize;

    public String getBlogType() {
        return blogType;
    }

    public void setBlogType(String blogType) {
        this.blogType = blogType;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage == 0) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
