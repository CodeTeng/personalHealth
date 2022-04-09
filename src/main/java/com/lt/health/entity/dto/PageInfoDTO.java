package com.lt.health.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 分页参数
 * @author: 狂小腾
 * @date: 2022/3/21 23:07
 */
@Data
@ApiModel("分页参数")
public class PageInfoDTO {

    /**
     * 当前页码
     */
    @ApiModelProperty("当前页码")
    private Integer pageNumber;

    /**
     * 页数
     */
    @ApiModelProperty("当前页显示多少页数")
    private Integer pageSize;

    /**
     * 分页查询的条件---用户名、昵称、手机号码
     */
    @ApiModelProperty("分页查询条件")
    private String queryString;
}
