package com.star.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_memory
 */
@TableName(value ="t_memory")
@Data
public class Memory implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    private String pictureAddress;

    /**
     * 
     */
    private String memory;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}