package com.lt.health.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品表
 *
 * @author 狂小腾
 * @TableName sys_goods
 */
@TableName(value = "sys_goods")
@Data
public class Goods implements Serializable {
    /**
     * 主键id
     */
    @ExcelIgnore
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String name;

    /**
     * 商品价格
     */
    @ExcelProperty(value = "商品价格")
    private Double price;

    /**
     * 商品数量
     */
    @ExcelProperty(value = "商品数量")
    private Integer number;

    /**
     * 入库时间
     */
    @ExcelProperty(value = "入库时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 入库的管理员
     */
    @ExcelIgnore
    private Long createUserId;

    /**
     * 图片地址
     */
    @ExcelIgnore
    private String imageUrl;

    /**
     * 适配机型
     */
    @ExcelProperty(value = "适配机型")
    private String modelType;

    /**
     * 控制方式
     */
    @ExcelProperty(value = "控制方式")
    private String controlMode;

    /**
     * 主要功能
     */
    @ExcelProperty(value = "主要功能")
    private String mainFunction;

    /**
     * 无线功能
     */
    @ExcelProperty(value = "无线功能")
    private String wifiFunction;

    /**
     * 电池规格
     */
    @ExcelProperty(value = "电池规格")
    private String battery;

    /**
     * 特色功能
     */
    @ExcelProperty(value = "特色功能")
    private String characteristic;

    /**
     * 外观尺寸
     */
    @ExcelProperty(value = "外观尺寸")
    private String size;

    /**
     * 其他功能
     */
    @ExcelProperty(value = "其他功能")
    private String other;

    /**
     * 备注
     */
    @ExcelIgnore
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}