package com.vanhieu.controller.web;

import com.vanhieu.service.IBlogService;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactPage {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/WEBPAGE/common/contact")
    public String showContactPage(Model model){
        if (ViewModelUtils.getCategories()  == null) {
            ViewModelUtils.setCategories(categoryService.findAll());
        }
        if (ViewModelUtils.getRecentBlogs() == null) {
            ViewModelUtils.setRecentBlogs(blogService.getRecentBlogs(3));
        }
        model.addAttribute("active", "idContact");
        model.addAttribute("categories", ViewModelUtils.getCategories());
        return "views/web/contact";
    }
}
