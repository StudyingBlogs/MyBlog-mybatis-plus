package com.star.controller;

import com.star.annotation.AccessLimit;
import com.star.domain.entity.Resources;
import com.star.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Participate
 * @date 2022/11/23 12:18
 */
@Controller
public class ResourceShowController {
    @Autowired
    private ResourcesService resourcesService;
    @GetMapping("/resources")
    public String resources(Model model){

        // 因为这里就这几种资源，所以写死了，有需要的可以加一个资源分类表
        String studyResource = "学习资源";
        String pictureResource = "图片资源";
        String officeResource = "办公资源";
        String recreationResource = "娱乐资源";
        String designResource = "设计资源";
        String searchResource = "搜索资源";
        String toolResource = "工具资源";

        model.addAttribute("studyresource", resourcesService.getStudyResource(studyResource));
        model.addAttribute("pictureresource",resourcesService.getPictureResource(pictureResource));
        model.addAttribute("officeresource",resourcesService.getOfficeResource(officeResource));
        model.addAttribute("recreationresource",resourcesService.getRecreationResource(recreationResource));
        model.addAttribute("designresource",resourcesService.getDesignResource(designResource));
        model.addAttribute("searchresource",resourcesService.getSearchResource(searchResource));
        model.addAttribute("toolresource",resourcesService.getToolResource(toolResource));

        model.addAttribute("studyResourceTotle",resourcesService.getStudyResourceTotle(studyResource));
        model.addAttribute("pictureResourceTotle",resourcesService.getPictureResourceTotle(pictureResource));
        model.addAttribute("officeResourceTotle",resourcesService.getOfficeResourceTotle(officeResource));
        model.addAttribute("recreationResourceTotle",resourcesService.getRecreationResourceTotle(recreationResource));
        model.addAttribute("designResourceTotle",resourcesService.getDesignResourceTotle(designResource));
        model.addAttribute("searchResourceTotle",resourcesService.getSearchResourceTotle(searchResource));
        model.addAttribute("toolResourceTotle",resourcesService.getToolResourceTotle(toolResource));


        return "resources";
    }
    @AccessLimit(seconds = 15, maxCount = 3) //15秒内 允许请求3次
    @PostMapping("/resources")
    public String post(@Valid Resources resources, BindingResult result, RedirectAttributes attributes){
        resources.setPublished(0);
        if(result.hasErrors()){
            return "admin/resources-input";
        }

        boolean P = resourcesService.save(resources);
        if (P == false ) {
            attributes.addFlashAttribute("message", "添加资源失败");
        } else {
            attributes.addFlashAttribute("message", "添加资源成功，管理员审核通过后即可在该页面查看，请耐心等待~");
        }
        return "redirect:/resources";

    }
}
