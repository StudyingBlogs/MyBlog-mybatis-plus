package com.star.service;

import com.star.domain.entity.Friend;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_friend】的数据库操作Service
* @createDate 2022-11-19 23:10:24
*/
public interface FriendService extends IService<Friend> {

    List<Friend> listFriendLink();

    Friend getFriendLinkByBlogaddress(String blogaddress);
}
