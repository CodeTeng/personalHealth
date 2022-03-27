package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sys_goods
 */
@TableName(value ="sys_goods")
@Data
public class Goods implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private Double price;

    /**
     * 
     */
    private Integer number;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Long createUserId;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 适配机型
     */
    private String modelType;

    /**
     * 控制方式
     */
    private String controlMode;

    /**
     * 主要功能
     */
    private String mainFunction;

    /**
     * 无线功能
     */
    private String wifiFunction;

    /**
     * 电池规格
     */
    private String battery;

    /**
     * 特色功能
     */
    private String characteristic;

    /**
     * 外观尺寸
     */
    private String size;

    /**
     * 其他功能
     */
    private String other;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}