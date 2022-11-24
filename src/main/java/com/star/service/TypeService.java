package com.star.service;

import com.star.domain.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_type】的数据库操作Service
* @createDate 2022-11-19 23:10:24
*/
public interface TypeService extends IService<Type> {

    List<Type> getAllType();


    Type getType(Long id);

    Type getTypeByName(String name);

    boolean saveType(Type type);

    boolean updateType(Type type);

    List<Type> getAllTypeAndBlog();
}
