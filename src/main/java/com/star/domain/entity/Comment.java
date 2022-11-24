package com.star.domain.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName t_comment
 */
@TableName(value ="t_comment")
@Data
public class Comment implements Serializable {
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
    private Long blogId;

    /**
     * 
     */

    private Long parentCommentId;

    /**
     * 
     */
    @TableField(fill = FieldFill.INSERT)
    private Boolean adminComment;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private String title;
    @TableField(exist = false)
    private List<Comment> replyComments = new ArrayList<>();
    @TableField(exist = false)
    private Comment parentComment;
    @TableField(exist = false)
    private String parentNickname;
}