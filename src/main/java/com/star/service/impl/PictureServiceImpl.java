package com.star.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.domain.entity.Picture;
import com.star.service.PictureService;
import com.star.mapper.PictureMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_picture】的数据库操作Service实现
* @createDate 2022-11-19 23:10:24
*/
@Service
public class PictureServiceImpl extends ServiceImpl<PictureMapper, Picture>
    implements PictureService{

    @Override
    //@Cacheable(value = "pictureList",key = "'picture'")
    public List<Picture> listPicture() {
        LambdaQueryWrapper<Picture> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Picture::getId);
        return list(queryWrapper);
    }
}




