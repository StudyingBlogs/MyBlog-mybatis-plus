package com.star.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName t_type
 */
@TableName(value ="t_type")
@Data
public class Type implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String name;
    @TableField(exist = false)
    private List<Blog> blogs = new ArrayList<>();

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}