package com.star.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.domain.entity.Blog;
import com.star.domain.entity.Comment;
import com.star.mapper.BlogMapper;
import com.star.service.BlogService;
import com.star.service.CommentService;
import com.star.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
* @author Participate
* @description 针对表【t_comment】的数据库操作Service实现
* @createDate 2022-11-19 23:10:24
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{
    @Autowired
    private BlogService blogService;
    private List<Comment> tempReplys = new ArrayList<>();
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        LambdaQueryWrapper<Comment> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Comment::getBlogId,blogId);
        queryWrapper1.eq(Comment::getParentCommentId,-1);
        queryWrapper1.orderByDesc(Comment::getCreateTime);
        List<Comment> comments = list(queryWrapper1);
        for (Comment comment : comments) {
            Long id = comment.getId();
            String parentNickName1 = comment.getNickname();
            LambdaQueryWrapper<Comment> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(Comment::getBlogId,blogId);
            queryWrapper2.eq(Comment::getParentCommentId,id);
            queryWrapper2.orderByDesc(Comment::getCreateTime);
            List<Comment> childComments = list(queryWrapper2);
            //查询子评论
            combineChildren(blogId,childComments,parentNickName1);
            comment.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        return comments;
    }

    @Override
    public Comment getEmailByParentId(Long parentId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getId,parentId);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        return getOne(queryWrapper);
    }

    @Override
    public void saveComment(Comment comment, Comment parentComment) {
        boolean save = save(comment);
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getBlogId,comment.getBlogId());
        int count = count(queryWrapper);
        Blog blog = blogService.getById(comment.getBlogId());
        blog.setCommentCount(count);
        blogService.updateById(blog);
        if(!StringUtils.isEmpty(parentComment)){
            String parentNickname = parentComment.getNickname();
            String nickName = comment.getNickname();
            String comtent = "亲爱的" + parentNickname + "，您在【ONESTARの客栈】的评论收到了来自" + nickName + "的回复！内容如下：" + "\r\n" + "\r\n" +  comment.getContent();
            String parentEmail = parentComment.getEmail();

            // 发送邮件
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setSubject("ONESTARの客栈评论回复");  //主题
            simpleMailMessage.setText(comtent);   //内容
            simpleMailMessage.setTo(parentEmail); //接收者的邮箱
            simpleMailMessage.setFrom("2711542571@qq.com");//发送者邮箱
            javaMailSender.send(simpleMailMessage);
        }
    }

    @Override
    public void deleteComment(Comment comment, Long id) {
        Comment comment1 = getById(id);
        removeById(id);
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getCommentCount,comment1.getBlogId());
        Blog blog = blogService.getOne(queryWrapper);
        blog.setCommentCount(blog.getCommentCount()-1);
        removeById(comment.getId());
    }

    private void combineChildren(Long blogId, List<Comment> childComments, String parentNickname1) {
        //判断是否有一级子评论
        if(childComments.size() > 0){
            //循环找出子评论的id
            for(Comment childComment : childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                tempReplys.add(childComment);
                Long childId = childComment.getId();
                //查询出子二级评论
                recursively(blogId, childId, parentNickname);
            }
        }
    }
    private void recursively(Long blogId, Long childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
//        List<Comment> replayComments = commentDao.findByBlogIdAndReplayId(blogId,childId);
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getBlogId,blogId);
        queryWrapper.eq(Comment::getParentCommentId,childId);
        queryWrapper.orderByDesc(Comment::getCreateTime);
        List<Comment> replayComments = list(queryWrapper);
        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Long replayId = replayComment.getId();
                tempReplys.add(replayComment);
                recursively(blogId,replayId,parentNickname);
            }
        }
    }

}




