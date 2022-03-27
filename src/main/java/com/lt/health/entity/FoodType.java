package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 食物分类表
 * @TableName sys_food_type
 */
@TableName(value ="sys_food_type")
@Data
public class FoodType implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 分类标题
     */
    private String title;

    /**
     * 分类图标
     */
    private String icon;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}