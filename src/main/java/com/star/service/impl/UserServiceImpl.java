package com.star.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.domain.entity.User;
import com.star.service.UserService;
import com.star.mapper.UserMapper;
import com.star.util.MD5Utils;
import org.springframework.stereotype.Service;

/**
* @author Participate
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2022-11-19 23:10:24
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public User checkUser(String username, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        queryWrapper.eq(User::getPassword, MD5Utils.code(password));
        User user = getOne(queryWrapper);
        return user;
    }
}




