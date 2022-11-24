package com.star.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_blog
 */
@TableName(value ="t_blog")
@Data
public class Blog implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    //@TableField(fill = FieldFill.INSERT)
    private Boolean appreciation;

    /**
     * 
     */
    //@TableField(fill = FieldFill.INSERT)
    private Boolean commentabled;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String firstPicture;

    /**
     * 
     */
    private String flag;

    /**
     * 
     */
    private Boolean published;

    /**
     * 
     */
    //@TableField(fill = FieldFill.INSERT)
    private Boolean recommend;

    /**
     * 
     */
    //@TableField(fill = FieldFill.INSERT)
    private Boolean shareStatement;

    /**
     * 
     */
    private String title;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer views;

    /**
     * 
     */
    private Long typeId;
    @TableField(exist = false)
    private Type type;
    /**
     * 
     */
    private Long userId;
    @TableField(exist = false)
    private User user;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer commentCount;
    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private String typeName;

    //用户名
    @TableField(exist = false)
    private String nickname;
}