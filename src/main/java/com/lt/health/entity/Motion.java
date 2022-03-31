package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 运动项目表
 *
 * @author 狂小腾
 * @TableName sys_motion
 */
@TableName(value = "sys_motion")
@Data
public class Motion implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 运动名称
     */
    private String name;

    /**
     * 使用年龄
     */
    private String applicableAge;

    /**
     * 收益部位(身体)
     */
    private String beneficialPosition;

    /**
     * 简介(对该运动的描述)
     */
    private String introduction;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}