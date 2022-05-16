package com.vanhieu.controller.web;

import com.vanhieu.dto.BlogDto;
import com.vanhieu.dto.CategoryDto;
import com.vanhieu.dto.ItemDto;
import com.vanhieu.dto.UserDto;
import com.vanhieu.service.IBlogService;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.service.IItemService;
import com.vanhieu.service.IUserService;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller(value = "HomeControllerOfWeb")
public class WebpageHomeController {

    @Autowired private ICategoryService categoryService;

    @Autowired private IItemService itemService;

    @Autowired private IUserService userService;

    @Autowired private IBlogService blogService;

    @GetMapping("/WEBPAGE")
    public String showPage(Model model, HttpServletRequest request, HttpServletResponse response) {

        ItemDto items = new ItemDto();
        Pageable pageable = PageRequest.of(0, 8);
        items.setListResult(itemService.findAll(pageable));
        if (request.getRemoteUser() != null) {
            UserDto user = userService.getUserByUsername(request.getRemoteUser());
            ViewModelUtils.setUser(user);
        }
        if (ViewModelUtils.getCategories()  == null) {
            ViewModelUtils.setCategories(categoryService.findAll());
        }
        if (ViewModelUtils.getRecentBlogs() == null) {
            ViewModelUtils.setRecentBlogs(blogService.getRecentBlogs(3));
        }
        model.addAttribute("categories", ViewModelUtils.getCategories());
        model.addAttribute("recentBlogs", ViewModelUtils.getRecentBlogs());
        model.addAttribute("items", items);
        model.addAttribute("active", "idHome");
        return "views/web/home";
    }
}
