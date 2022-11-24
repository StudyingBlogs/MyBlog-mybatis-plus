package com.star.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_friend
 */
@TableName(value ="t_friend")
@Data
public class Friend implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String blogaddress;

    /**
     * 
     */
    private String blogname;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    private String pictureaddress;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}