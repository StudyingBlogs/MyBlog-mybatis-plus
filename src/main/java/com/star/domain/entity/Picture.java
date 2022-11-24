package com.star.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_picture
 */
@TableName(value ="t_picture")
@Data
public class Picture implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String pictureaddress;

    /**
     * 
     */
    private String picturedescription;

    /**
     * 
     */
    private String picturename;

    /**
     * 
     */
    private String picturetime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}