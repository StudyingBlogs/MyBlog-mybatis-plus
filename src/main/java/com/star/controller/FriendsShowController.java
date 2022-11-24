package com.star.controller;

import com.star.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Participate
 * @date 2022/11/23 12:12
 */
@Controller
public class FriendsShowController {
    @Autowired
    private FriendService friendService;
    @GetMapping("/friends")
    public String friends(Model model) {
        model.addAttribute("friendlinks",friendService.listFriendLink());
        return "friends";
    }
}
