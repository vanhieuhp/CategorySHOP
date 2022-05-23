package com.vanhieu.controller.web;

import com.vanhieu.dto.ItemDto;
import com.vanhieu.service.IBlogService;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.service.IItemService;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopGridController {

    @Autowired
    private IItemService itemService;

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/WEBPAGE/common/shopGrid")
    public String showShopGrid(Model model, HttpServletRequest request) {
        if (ViewModelUtils.getCategories()  == null) {
            ViewModelUtils.setCategories(categoryService.findAll());
        }
        if (ViewModelUtils.getRecentBlogs() == null) {
            ViewModelUtils.setRecentBlogs(blogService.getRecentBlogs(3));
        }
        ItemDto items = new ItemDto();
        Pageable pageable = PageRequest.of(0, 8);
        items.setListResult(itemService.findAll(pageable));
        String id = "1";
        if (request.getParameter("id") != null) {
            id = request.getParameter("id");
        }
        model.addAttribute("categoryId", id);
        model.addAttribute("categories", ViewModelUtils.getCategories());
        model.addAttribute("recentBlogs", ViewModelUtils.getRecentBlogs());
        model.addAttribute("items", items);
        model.addAttribute("active", "idShopGrid");
        return "/views/web/shopGrid";
    }
}
