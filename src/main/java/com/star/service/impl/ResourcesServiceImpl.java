package com.star.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.domain.entity.Resources;
import com.star.service.ResourcesService;
import com.star.mapper.ResourcesMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_resources】的数据库操作Service实现
* @createDate 2022-11-19 23:10:24
*/
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources>
    implements ResourcesService{

    @Override
    public List<Resources> listResources() {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Resources::getId);
        return list(queryWrapper);
    }

    @Override
    public void changePublished(boolean b, Long id) {
        UpdateWrapper<Resources> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("published",b);
        update(updateWrapper);
    }

    @Override
    //@Cacheable(value = "studyResource",key = "'studyResourceList'")
    public List<Resources> getStudyResource(String studyResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,studyResource);
        queryWrapper.eq(Resources::getPublished,1);
        queryWrapper.orderByAsc(Resources::getSort);
        return list(queryWrapper);
    }

    @Override
    //@Cacheable(value = "pictureResource",key = "'pictureResourceList'")
    public List<Resources> getPictureResource(String pictureResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,pictureResource);
        queryWrapper.eq(Resources::getPublished,1);
        queryWrapper.orderByAsc(Resources::getSort);
        return list(queryWrapper);
    }

    @Override
    //@Cacheable(value = "officeResource",key = "'officeResourceList'")
    public List<Resources> getOfficeResource(String officeResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,officeResource);
        queryWrapper.eq(Resources::getPublished,1);
        queryWrapper.orderByAsc(Resources::getSort);
        return list(queryWrapper);
    }

    @Override
    //@Cacheable(value = "recreationResource",key = "'recreationResourceList'")
    public List<Resources> getRecreationResource(String recreationResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,recreationResource);
        queryWrapper.eq(Resources::getPublished,1);
        queryWrapper.orderByAsc(Resources::getSort);
        return list(queryWrapper);
    }

    @Override
    //@Cacheable(value = "designResource",key = "'designResourceList'")
    public List<Resources> getDesignResource(String designResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,designResource);
        queryWrapper.eq(Resources::getPublished,1);
        queryWrapper.orderByAsc(Resources::getSort);
        return list(queryWrapper);
    }

    @Override
    //@Cacheable(value = "searchResource",key = "'searchResourceList'")
    public List<Resources> getSearchResource(String searchResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,searchResource);
        queryWrapper.eq(Resources::getPublished,1);
        queryWrapper.orderByAsc(Resources::getSort);
        return list(queryWrapper);
    }

    @Override
    //@Cacheable(value = "toolResource",key = "'toolResourceList'")
    public List<Resources> getToolResource(String toolResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,toolResource);
        queryWrapper.eq(Resources::getPublished,1);
        queryWrapper.orderByAsc(Resources::getSort);
        return list(queryWrapper);
    }

    @Override
    public Integer getStudyResourceTotle(String studyResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,studyResource);
        queryWrapper.eq(Resources::getPublished,1);
        return count(queryWrapper);
    }

    @Override
    public Integer getPictureResourceTotle(String pictureResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,pictureResource);
        queryWrapper.eq(Resources::getPublished,1);
        return count(queryWrapper);
    }

    @Override
    public Integer getOfficeResourceTotle(String officeResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,officeResource);
        queryWrapper.eq(Resources::getPublished,1);
        return count(queryWrapper);
    }

    @Override
    public Integer getRecreationResourceTotle(String recreationResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,recreationResource);
        queryWrapper.eq(Resources::getPublished,1);
        return count(queryWrapper);
    }

    @Override
    public Integer getDesignResourceTotle(String designResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,designResource);
        queryWrapper.eq(Resources::getPublished,1);
        return count(queryWrapper);
    }

    @Override
    public Integer getSearchResourceTotle(String searchResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,searchResource);
        queryWrapper.eq(Resources::getPublished,1);
        return count(queryWrapper);
    }

    @Override
    public Integer getToolResourceTotle(String toolResource) {
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Resources::getFirstType,toolResource);
        queryWrapper.eq(Resources::getPublished,1);
        return count(queryWrapper);
    }
}




