package com.star.mapper;

import com.star.domain.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Participate
* @description 针对表【t_user】的数据库操作Mapper
* @createDate 2022-11-19 23:10:24
* @Entity com.star.domain.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




