package com.lt.health.constant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: 结果集
 * @author: 狂小腾
 * @date: 2022/3/21 22:53
 */
@Data
@ApiModel("同一返回结果实体类")
public class Result implements Serializable {
    /**
     * 响应给前端是否成功的标识
     */
    @ApiModelProperty("是否成功 true-成功 false-失败")
    private boolean flag;

    /**
     * 响应信息
     */
    @ApiModelProperty("响应信息")
    private String message;

    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private Object data;

    public Result() {
    }

    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    /**
     * 响应成功的结果
     */
    public static Result success(String message, Object data) {
        return new Result(true, message, data);
    }

    /**
     * 响应成功的结果
     */
    public static Result success(String message) {
        return new Result(true, message);
    }

    /**
     * 响应失败的结果
     */
    public static Result fail(String message) {
        return new Result(false, message);
    }
}