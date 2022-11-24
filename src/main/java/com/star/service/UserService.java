package com.star.service;

import com.star.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Participate
* @description 针对表【t_user】的数据库操作Service
* @createDate 2022-11-19 23:10:24
*/
public interface UserService extends IService<User> {

    User checkUser(String username, String password);
}
