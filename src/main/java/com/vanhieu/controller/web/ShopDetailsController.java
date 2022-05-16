package com.vanhieu.controller.web;

import com.vanhieu.dto.ItemDto;
import com.vanhieu.service.IBlogService;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.service.IItemService;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ShopDetailsController {

    @Autowired
    private IItemService itemService;

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/WEBPAGE/common/shopDetails/{id}")
    public String showPageDetails(@PathVariable("id") Long id, Model model) {
        if (ViewModelUtils.getCategories()  == null) {
            ViewModelUtils.setCategories(categoryService.findAll());
        }
        if (ViewModelUtils.getRecentBlogs() == null) {
            ViewModelUtils.setRecentBlogs(blogService.getRecentBlogs(3));
        }

        ItemDto itemModel = itemService.getOne(id);
        List<ItemDto> items = itemService.findByCategoryCode(itemModel.getCategoryCode(), 7);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                items.remove(i);
            }
        }
        model.addAttribute("categories", ViewModelUtils.getCategories());
        model.addAttribute("itemModel", itemModel);
        model.addAttribute("items", items);
        return "views/web/shopDetails";
    }
}
