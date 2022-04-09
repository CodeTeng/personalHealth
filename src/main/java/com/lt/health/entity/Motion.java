package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("运动项目实体类")
public class Motion implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty("主键id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 运动名称
     */
    @ApiModelProperty("运动名称")
    private String name;

    /**
     * 适用年龄
     */
    @ApiModelProperty("适用年龄")
    private String applicableAge;

    /**
     * 身体收益部位
     */
    @ApiModelProperty("身体收益部位")

    private String beneficialPosition;

    /**
     * 简介(对该运动的描述)
     */
    @ApiModelProperty("简介")
    private String introduction;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}