package com.star.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.domain.entity.Friend;
import com.star.service.FriendService;
import com.star.mapper.FriendMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_friend】的数据库操作Service实现
* @createDate 2022-11-19 23:10:24
*/
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
    implements FriendService{

    @Override
    //@Cacheable(value = "friendlinkList",key = "'friendlink'")
    public List<Friend> listFriendLink() {
        LambdaQueryWrapper<Friend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Friend::getCreateTime);
        return list(queryWrapper);
    }

    @Override
    public Friend getFriendLinkByBlogaddress(String blogaddress) {
        LambdaQueryWrapper<Friend> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Friend::getBlogaddress,blogaddress);
        return getOne(queryWrapper);
    }
}




