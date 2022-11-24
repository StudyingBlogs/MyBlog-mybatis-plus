package com.star.mapper;

import com.star.domain.entity.Friend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Participate
* @description 针对表【t_friend】的数据库操作Mapper
* @createDate 2022-11-19 23:10:24
* @Entity com.star.domain.entity.Friend
*/
@Mapper
public interface FriendMapper extends BaseMapper<Friend> {

}




