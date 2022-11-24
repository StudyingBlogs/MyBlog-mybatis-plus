package com.star.controller;

import com.star.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Participate
 * @date 2022/11/23 12:08
 */
@Controller
public class ArchiveShowController {
    @Autowired
    private MemoryService memoryService;
    @GetMapping("/archives")
    public String archive(Model model){
        model.addAttribute("memorys",  memoryService.listMemory());
        return "archives";
    }
}
