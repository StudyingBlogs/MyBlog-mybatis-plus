package com.star.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.domain.entity.Friend;
import com.star.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author Participate
 * @date 2022/11/20 23:52
 */
@Controller
@RequestMapping("/admin")
public class FriendController {
    @Autowired
    private FriendService friendService;
    @GetMapping("/friendlinks")
    public String friend(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,10);
        List<Friend> listFriendLink = friendService.listFriendLink();
        PageInfo<Friend> pageInfo = new PageInfo<Friend>(listFriendLink);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/friendlinks";
    }
    @GetMapping("/friendlinks/input")
    public String input(Model model) {
        model.addAttribute("friendlink", new Friend());
        return "admin/friendlinks-input";
    }

    //友链新增
    @PostMapping("/friendlinks")
    public String post(@Valid Friend friendLink, BindingResult result, RedirectAttributes attributes){

        Friend type1 = friendService.getFriendLinkByBlogaddress(friendLink.getBlogaddress());
        if (type1 != null) {
            attributes.addFlashAttribute("message", "不能添加相同的网址");
            return "redirect:/admin/friendlinks/input";
        }

        if(result.hasErrors()){
            return "admin/friendlinks-input";
        }
        friendLink.setCreateTime(new Date());
        boolean F = friendService.save(friendLink);
        if (F == false ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/friendlinks";
    }
    @GetMapping("/friendlinks/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("friendlink", friendService.getById(id));
        return "admin/friendlinks-input";
    }

    //编辑修改友链
    @PostMapping("/friendlinks/{id}")
    public String editPost(@Valid Friend friendLink, RedirectAttributes attributes) {
        boolean t = friendService.updateById(friendLink);
        if (t == false ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/friendlinks";
    }
    @GetMapping("/friendlinks/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        friendService.removeById(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/friendlinks";
    }
}
