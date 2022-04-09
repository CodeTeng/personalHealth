package com.lt.health.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 运动咨询
 * @author 狂小腾
 * @TableName sys_sport
 */
@TableName(value ="sys_sport")
@Data
@ApiModel("运动咨询实体类")
public class Sport implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建者")
    private String createName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新者")
    private String updateName;

    /**
     * 删除标记---逻辑删除
     */
    @TableLogic
    @ApiModelProperty(value = "删除标记")
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}