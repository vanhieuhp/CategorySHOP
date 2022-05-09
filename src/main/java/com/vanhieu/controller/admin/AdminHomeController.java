package com.vanhieu.controller.admin;

import com.vanhieu.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "HomeControllerOfAdmin")
public class AdminHomeController {

    @Autowired
    private IItemService itemService;

    @GetMapping("/ADMIN/home")
    public ModelAndView showPage(HttpServletRequest request) {
        ModelAndView  mav = new ModelAndView("views/admin/home");
        return mav;
    }
}
