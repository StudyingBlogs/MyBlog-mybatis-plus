package com.star.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.domain.entity.Blog;
import com.star.domain.entity.Type;
import com.star.service.BlogService;
import com.star.service.TypeService;
import com.star.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_type】的数据库操作Service实现
* @createDate 2022-11-19 23:10:24
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{
    @Autowired
    private BlogService blogService;
    @Override
    public List<Type> getAllType() {
        return list();
    }

    @Override
    public Type getType(Long id) {
        return getById(id);
    }

    @Override
    public Type getTypeByName(String name) {
        LambdaQueryWrapper<Type> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Type::getName,name);
        return getOne(queryWrapper);
    }

    @Override
    public boolean saveType(Type type) {
        return save(type);
    }

    @Override
    public boolean updateType(Type type) {
        return updateById(type);
    }

    @Override
    public List<Type> getAllTypeAndBlog() {
        List<Type> types = list();
        for (Type type : types) {
            LambdaQueryWrapper<Blog> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Blog::getTypeId,type.getId());
            type.setBlogs(blogService.list(queryWrapper));
        }
        return types;
    }
}




