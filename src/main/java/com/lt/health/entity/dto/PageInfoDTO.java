package com.lt.health.entity.dto;

import lombok.Data;

/**
 * @description: 分页参数
 * @author: 狂小腾
 * @date: 2022/3/21 23:07
 */
@Data
public class PageInfoDTO {

    /**
     * 当前页码
     */
    private Integer pageNumber;

    /**
     * 页数
     */
    private Integer pageSize;

    /**
     * 分页查询的条件---用户名、昵称、手机号码
     */
    private String queryString;
}
