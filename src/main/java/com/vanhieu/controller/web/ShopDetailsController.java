package com.vanhieu.controller.web;

import com.vanhieu.dto.ItemDto;
import com.vanhieu.service.IItemService;
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

    @GetMapping("/WEBPAGE/shopDetails/{id}")
    public String showPageDetails(@PathVariable("id") Long id, Model model) {
        ItemDto itemModel = itemService.getOne(id);
        List<ItemDto> items = itemService.findByCategoryCode(itemModel.getCategoryCode(), 7);
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == id) {
                items.remove(i);
            }
        }
        model.addAttribute("itemModel", itemModel);
        model.addAttribute("items", items);
        return "views/web/shopDetails";
    }
}
