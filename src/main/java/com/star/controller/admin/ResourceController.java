package com.star.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.domain.entity.Resources;
import com.star.service.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Participate
 * @date 2022/11/21 9:14
 */
@Controller
@RequestMapping("/admin")
public class ResourceController {
    @Autowired
    private ResourcesService resourcesService;
    private Integer flage;

    @GetMapping("/resources")
    public String pictures(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Resources> listResources = resourcesService.listResources();
        PageInfo<Resources> pageInfo = new PageInfo<Resources>(listResources);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/resources";
    }
    @GetMapping("/resources/input")
    public String input(Model model) {
        model.addAttribute("resource", new Resources());
        return "admin/resources-input";
    }


    //    资源新增
    @PostMapping("/resources")
    public String post(@Valid Resources resources, BindingResult result, RedirectAttributes attributes){
        resources.setPublished(0);
        if(result.hasErrors()){
            return "admin/resources-input";
        }

        boolean P = resourcesService.save(resources);
        if (P == false ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/resources";
    }
    @GetMapping("/resources/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("resource", resourcesService.getById(id));
        return "admin/resources-input";
    }

    //    编辑相册
    @PostMapping("/resources/{id}")
    public String editPost(@Valid Resources resources, RedirectAttributes attributes) {

        boolean P = resourcesService.updateById(resources);
        if (P == false ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/resources";
    }
    @GetMapping("/resources/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        resourcesService.removeById(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/resources";
    }
    @GetMapping("/resources/{id}/public")
    public String is_published(@PathVariable Long id, RedirectAttributes attributes){

        Resources resources = resourcesService.getById(id);

        flage = resources.getPublished();
        if(flage == 0){
            resourcesService.changePublished(true,id);
            attributes.addFlashAttribute("message", "成功发布");
            flage = 1;
        }else{
            resourcesService.changePublished(false,id);
            attributes.addFlashAttribute("message", "取消发布");
            flage = 0;
        }

        return "redirect:/admin/resources";
    }
}
