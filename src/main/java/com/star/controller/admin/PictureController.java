package com.star.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.domain.entity.Picture;
import com.star.service.PictureService;
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
 * @date 2022/11/21 9:06
 */
@Controller
@RequestMapping("/admin")
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @GetMapping("/pictures")
    public String pictures(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Picture> listFriendLink = pictureService.listPicture();
        //List<Picture> listFriendLink = testService1.list();
        PageInfo<Picture> pageInfo = new PageInfo<Picture>(listFriendLink);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/pictures";
    }

    @GetMapping("/pictures/input")
    public String input(Model model) {
        model.addAttribute("picture", new Picture());
        return "admin/pictures-input";
    }

    //    照片新增
    @PostMapping("/pictures")
    public String post(@Valid Picture picture, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            return "admin/pictures-input";
        }

        boolean P = pictureService.save(picture);
        System.out.println("pictureId:" + picture);
        if (P == false ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/pictures";
    }

    @GetMapping("/pictures/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("picture", pictureService.getById(id));
        return "admin/pictures-input";
    }

    //    编辑相册
    @PostMapping("/pictures/{id}")
    public String editPost(@Valid Picture picture, RedirectAttributes attributes) {

        boolean P = pictureService.updateById(picture);
        if (P == false ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/pictures";
    }
    @GetMapping("/pictures/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        pictureService.removeById(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/pictures";
    }


}
