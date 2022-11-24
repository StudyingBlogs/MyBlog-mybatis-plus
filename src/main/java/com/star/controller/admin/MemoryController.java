package com.star.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.domain.entity.Memory;
import com.star.service.MemoryService;
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
 * @date 2022/11/20 23:30
 */
@Controller
@RequestMapping("/admin")
public class MemoryController {
    @Autowired
    private MemoryService memoryService;

    @GetMapping("/memorys")
    public String pictures(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Memory> listMemory = memoryService.listMemory();
        PageInfo<Memory> pageInfo = new PageInfo<Memory>(listMemory);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/memorys";
    }
    @GetMapping("/memorys/input")
    public String input(Model model) {
        model.addAttribute("memory", new Memory());
        return "admin/memorys-input";
    }
    @PostMapping("/memorys")
    public String post(@Valid Memory memory, BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            return "admin/memorys-input";
        }

        memory.setCreateTime(new Date());
        boolean P = memoryService.saveMemory(memory);
        if (P == false ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/memorys";
    }
    @GetMapping("/memorys/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("memory", memoryService.getById(id));
        return "admin/memorys-input";
    }

    //    编辑流年
    @PostMapping("/memorys/{id}")
    public String editPost(@Valid Memory memory, RedirectAttributes attributes) {

        boolean P = memoryService.updateById(memory);
        if (P == false ) {
            attributes.addFlashAttribute("message", "编辑失败");
        } else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/memorys";
    }
    @GetMapping("/memorys/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        memoryService.removeById(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/memorys";
    }
}
