package com.star.controller;

import com.star.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Participate
 * @date 2022/11/23 12:16
 */
@Controller
public class PictureShowController {
    @Autowired
    private PictureService pictureService;
    @GetMapping("/picture")
    public String friends(Model model) {
        model.addAttribute("pictures",pictureService.listPicture());
        return "picture";
    }
}
