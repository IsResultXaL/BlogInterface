package com.caogen.blog.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User {

    @ApiModelProperty(hidden = true)
    private Integer id;

    private String userName;

    private String password;

    @ApiModelProperty(hidden = true)
    private List<String> roles;

    @ApiModelProperty(hidden = true)
    private Date lastPasswordResetDate;

}
