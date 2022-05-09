package com.vanhieu.controller;

import com.vanhieu.dto.UserDto;
import com.vanhieu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @Autowired
    private IUserService userService;

    @GetMapping("/api/test/user")
    public String testMethod(Model model) {
        UserDto userDto = userService.getOne(1L);
        model.addAttribute("model", userDto);
        System.out.println(userDto.toString());
        return "views/test";
    }
}
