package com.caogen.blog.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BlogCondition {

    private String blogType;

    private String searchKey;

    private int currentPage;

    @ApiModelProperty(hidden = true)
    private int offset;

    private int pageSize;
}
