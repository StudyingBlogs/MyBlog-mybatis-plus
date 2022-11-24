package com.star.service;

import com.star.domain.entity.Resources;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Participate
* @description 针对表【t_resources】的数据库操作Service
* @createDate 2022-11-19 23:10:24
*/
public interface ResourcesService extends IService<Resources> {

    List<Resources> listResources();

    void changePublished(boolean b, Long id);

    List<Resources> getStudyResource(String studyResource);

    List<Resources> getPictureResource(String pictureResource);

    List<Resources> getOfficeResource(String officeResource);

    List<Resources> getRecreationResource(String recreationResource);

    List<Resources> getDesignResource(String designResource);

    List<Resources> getSearchResource(String searchResource);

    List<Resources> getToolResource(String toolResource);

    Integer getStudyResourceTotle(String studyResource);

    Integer getPictureResourceTotle(String pictureResource);

    Integer getOfficeResourceTotle(String officeResource);

    Integer getRecreationResourceTotle(String recreationResource);

    Integer getDesignResourceTotle(String designResource);

    Integer getSearchResourceTotle(String searchResource);

    Integer getToolResourceTotle(String toolResource);
}
