package com.star.service;

import com.star.domain.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.star.domain.vo.*;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_blog】的数据库操作Service
* @createDate 2022-11-19 23:10:24
*/
public interface BlogService extends IService<Blog> {

    List<BlogQuery> getAllBlog();

    ShowBlog getBlogById(Long id);

    boolean saveBlog(ShowBlog showBlog);

    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);

    List<FirstPageBlog> getAllFirstPageBlog();

    List<RecommendBlog> getRecommendedBlog();

    List<NewComment> getNewComment();

    List<FirstPageBlog> getSearchBlog(String query);

    int getBlogViewTotal();

    int getBlogCommentTotal();

    int getBlogMessageTotal();

    DetailedBlog getDetailedBlog(Long id);

    List<FirstPageBlog> getByTypeId(Long id);
}
