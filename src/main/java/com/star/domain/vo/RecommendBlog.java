package com.star.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 推荐博客数据实体类
 * @Date: Created in 9:47 2020/6/19
 * @Author: ONESTAR
 * @QQ群: 530311074
 * @URL: https://onestar.newstar.net.cn/
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendBlog {

    private Long id;
    private String title;
    private String firstPicture;
    private boolean recommend;


}