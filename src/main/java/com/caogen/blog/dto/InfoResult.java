package com.caogen.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description= "返回响应数据")
@Data
@Accessors(chain = true)
public class InfoResult<T> {

    @ApiModelProperty(value = "状态码")
    private int code;

    @ApiModelProperty(value = "返回信息")
    private String msg;

    @ApiModelProperty(value = "返回对象")
    private T data;

    public InfoResult(int code) {
        this.code = code;
    }

}
