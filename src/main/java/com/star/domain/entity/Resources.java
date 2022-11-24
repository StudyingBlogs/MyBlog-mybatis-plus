package com.star.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_resources
 */
@TableName(value ="t_resources")
@Data
public class Resources implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String resourceName;

    /**
     * 
     */
    private String resourceAddress;

    /**
     * 
     */
    private String firstType;

    /**
     * 
     */
    private String secondType;

    /**
     * 
     */
    private String pictureAddress;

    /**
     * 
     */
    private String resourceDescription;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    private Integer sort;

    /**
     * 
     */
    private Integer published;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}