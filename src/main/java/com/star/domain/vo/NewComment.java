package com.star.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 最新评论实体类
 * @Date: Created in 19:45 2020/8/19
 * @Author: ONESTAR
 * @QQ群: 530311074
 * @URL: https://onestar.newstar.net.cn/
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewComment {

    private Long id;
    private Long blogId;
    private String title;
    private String content;


}