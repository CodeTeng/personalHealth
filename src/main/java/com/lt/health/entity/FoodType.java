package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 食物分类表
 *
 * @author: 狂小腾
 * @TableName sys_food_type
 */
@TableName(value = "sys_food_type")
@Data
@ApiModel("食物分类实体类")
public class FoodType implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Long id;

    /**
     * 分类标题
     */
    @ApiModelProperty("分类标题")
    private String title;

    /**
     * 分类图标
     */
    @ApiModelProperty("分类图标")
    private String icon;

    /**
     * 食物下的分类
     */
    @TableField(exist = false)
    @ApiModelProperty("食物下的分类")
    private List<Food> foods;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}