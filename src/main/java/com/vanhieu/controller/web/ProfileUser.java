package com.vanhieu.controller.web;

import com.vanhieu.service.IUserService;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileUser {

    @Autowired
    private IUserService userService;

    @GetMapping("/WEBPAGE/profile")
    public String showProfilePage(Model model, HttpServletRequest request) {

        model.addAttribute("userProfile",ViewModelUtils.getUser());
        return "views/web/profileUser";
    }
}
