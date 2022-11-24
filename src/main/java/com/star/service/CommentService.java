package com.star.service;

import com.star.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_comment】的数据库操作Service
* @createDate 2022-11-19 23:10:24
*/
public interface CommentService extends IService<Comment> {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment getEmailByParentId(Long parentId);

    void saveComment(Comment comment, Comment parentComment);

    void deleteComment(Comment comment, Long id);
}
