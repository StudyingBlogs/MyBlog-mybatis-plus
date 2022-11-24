package com.star.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName t_message
 */
@TableName(value ="t_message")
@Data
public class Message implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String avatar;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 
     */
    private Long parentMessageId;
    @TableField(exist = false)
    private String parentNickname;
    @TableField(exist = false)
    private List<Message> replyMessages = new ArrayList<>();
    @TableField(exist = false)
    private Message parentMessage;
    @TableField
    private String parentEmail;
    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Boolean adminMessage;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}