package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 食物详情表
 * @TableName sys_food
 */
@TableName(value ="sys_food")
@Data
public class Food implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 食物名称
     */
    private String title;

    /**
     * 食物类别
     */
    private Long typeId;

    /**
     * 图片(列表)
     */
    private String imageUrls;

    /**
     * 营养元素
     */
    private String nutrient;

    /**
     * 热量
     */
    private Double heat;

    /**
     * 蛋白质
     */
    private Double protein;

    /**
     * 脂肪
     */
    private Double fat;

    /**
     * 碳水化合物
     */
    private Double carbonWater;

    /**
     * 膳食纤维
     */
    private Double dietaryFiber;

    /**
     * 维生素A
     */
    private Double vitaminA;

    /**
     * 维生素C
     */
    private Double vitaminC;

    /**
     * 维生素E
     */
    private Double vitaminE;

    /**
     * 胡萝卜素
     */
    private Double carrot;

    /**
     * 维生素B1
     */
    private Double vitaminB1;

    /**
     * 维生素B2
     */
    private Double vitaminB2;

    /**
     * 烟酸
     */
    private Double niacin;

    /**
     * 胆固醇
     */
    private Double cholesterol;

    /**
     * 镁
     */
    private Double magnesium;

    /**
     * 铁
     */
    private Double iron;

    /**
     * 钙
     */
    private Double calcium;

    /**
     * 锌
     */
    private Double zinc;

    /**
     * 铜
     */
    private Double copper;

    /**
     * 锰
     */
    private Double manganese;

    /**
     * 钾
     */
    private Double potassium;

    /**
     * 磷
     */
    private Double phosphorus;

    /**
     * 钠
     */
    private Double sodium;

    /**
     * 硒
     */
    private Double selenium;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}