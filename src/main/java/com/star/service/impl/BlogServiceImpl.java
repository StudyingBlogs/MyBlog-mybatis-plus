package com.star.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.NotFoundException;
import com.star.domain.entity.Blog;
import com.star.domain.entity.Comment;
import com.star.domain.entity.Type;
import com.star.domain.vo.*;
import com.star.service.*;
import com.star.mapper.BlogMapper;
import com.star.util.BeanCopyUtils;
import com.star.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author Participate
* @description 针对表【t_blog】的数据库操作Service实现
* @createDate 2022-11-19 23:10:24
*/
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog>
    implements BlogService{
    @Autowired
    private TypeService typeService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MessageService messageService;
    @Override
    public List<BlogQuery> getAllBlog() {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Blog::getCreateTime);
        List<Blog> blogs = list(queryWrapper);
        for (Blog blog : blogs) {
            Long typeId = blog.getTypeId();
            Type type = typeService.getById(typeId);
            blog.setType(type);
        }
        return BeanCopyUtils.copyBeanList(blogs, BlogQuery.class);
    }

    @Override
    public ShowBlog getBlogById(Long id) {
        Blog blog = getById(id);
        return BeanCopyUtils.copyBean(blog, ShowBlog.class);
    }

    @Override
    public boolean saveBlog(ShowBlog showBlog) {
        Blog blog = BeanCopyUtils.copyBean(showBlog, Blog.class);
        return updateById(blog);
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!Objects.isNull(searchBlog.getTitle()),Blog::getTitle,searchBlog.getTitle());
        queryWrapper.eq(!Objects.isNull(searchBlog.getTypeId()),Blog::getTypeId,searchBlog.getTypeId());
        List<Blog> blogs = list(queryWrapper);
        for (Blog blog : blogs) {
            blog.setType(typeService.getById(blog.getTypeId()));
        }
        List<BlogQuery> blogQueries = BeanCopyUtils.copyBeanList(blogs, BlogQuery.class);
        return blogQueries;
    }

    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getPublished,1);
        queryWrapper.orderByDesc(Blog::getCreateTime);
        List<Blog> blogs = list(queryWrapper);
        for (Blog blog : blogs) {
            blog.setTypeName(typeService.getById(blog.getTypeId()).getName());
            blog.setAvatar(userService.getById(blog.getUserId()).getAvatar());
            blog.setNickname(userService.getById(blog.getUserId()).getNickname());
        }
        return BeanCopyUtils.copyBeanList(blogs, FirstPageBlog.class);
    }

    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("recommend",true);
        queryWrapper.orderByDesc("create_time");
        queryWrapper.last("limit 4");
        return BeanCopyUtils.copyBeanList(list(queryWrapper),RecommendBlog.class);
    }

    @Override
    public List<NewComment> getNewComment() {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        queryWrapper.last("limit 10");
        List<Comment> comments = commentService.list(queryWrapper);
        for (Comment comment : comments) {
            comment.setTitle(getById(comment.getBlogId()).getTitle());
        }
        return BeanCopyUtils.copyBeanList(comments, NewComment.class);
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Blog::getTitle,query).or().like(Blog::getContent,query);
        queryWrapper.orderByDesc(Blog::getCreateTime);
        List<Blog> blogs = list(queryWrapper);
        for (Blog blog : blogs) {
            blog.setTypeName(typeService.getById(blog.getTypeId()).getName());
            blog.setAvatar(userService.getById(blog.getUserId()).getAvatar());
            blog.setNickname(userService.getById(blog.getUserId()).getNickname());
        }
        return BeanCopyUtils.copyBeanList(blogs,FirstPageBlog.class);
    }

    @Override
    public int getBlogViewTotal() {
        int sum = 0;
        List<Blog> blogs = list();
        for (Blog blog : blogs) {
            sum += blog.getViews();
        }
        return sum;
    }

    @Override
    public int getBlogCommentTotal() {
        return commentService.count();
    }

    @Override
    public int getBlogMessageTotal() {
        return messageService.count();
    }

    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        Blog blog = getById(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        blog.setAvatar(userService.getById(blog.getUserId()).getAvatar());
        blog.setTypeName(typeService.getById(blog.getTypeId()).getName());
        blog.setNickname(userService.getById(blog.getUserId()).getNickname());
        String content = blog.getContent();
        blog.setViews(blog.getViews()+1);
        updateById(blog);

        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return BeanCopyUtils.copyBean(blog, DetailedBlog.class);
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long id) {
        LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Blog::getTypeId,id);
        queryWrapper.orderByDesc(Blog::getUpdateTime);
        List<Blog> blogs = list(queryWrapper);
        for (Blog blog : blogs) {
            blog.setNickname(userService.getById(blog.getUserId()).getNickname());
            blog.setAvatar(userService.getById(blog.getUserId()).getAvatar());
            blog.setTypeName(typeService.getById(id).getName());
        }
        return BeanCopyUtils.copyBeanList(blogs, FirstPageBlog.class);
    }

}




