package com.vanhieu.controller.admin;

import com.vanhieu.dto.ItemDto;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.service.IItemService;
import com.vanhieu.util.MessageUtils;
import com.vanhieu.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ItemController {


    @Autowired private IItemService itemService;

    @Autowired private ICategoryService categoryService;

    @GetMapping("/ADMIN/item/list")
    public String showList(@RequestParam("page") int page,
                           @RequestParam("limit") int limit,
                           HttpServletRequest request,
                           Model model) {
        ItemDto itemModel = new ItemDto();
        itemModel.setPage(page);
        itemModel.setLimit(limit);
        Pageable pageable = PageRequest.of(itemModel.getPage() - 1, itemModel.getLimit());
        itemModel.setListResult(itemService.findAll(pageable));
        itemModel.setTotalItem(itemService.count());
        itemModel.setTotalPage((int) Math.ceil( (double) itemModel.getTotalItem() / itemModel.getLimit()));
        String message = request.getParameter("message");
        if (message != null) {
            Map<String, String> messages = MessageUtils.getMessage(message);
            model.addAttribute("message", messages.get("message"));
            model.addAttribute("alert", messages.get("alert"));
        }
        model.addAttribute("itemModel", itemModel);
        return "views/admin/item/list";
    }

    @GetMapping("/ADMIN/item/edit")
    public String showEditPage(@RequestParam("limit") int limit,
                               HttpServletRequest request,
                               Model model) {
        ItemDto itemModel = new ItemDto();
        if (request.getParameter("id") != null) {
            Long id = Long.valueOf(request.getParameter("id"));
            itemModel = itemService.getOne(id);
            model.addAttribute("pageTitle", "Edit item (ID: " + itemModel.getId() + ")");
        } else {
            model.addAttribute("pageTitle", "Add new item");
        }

        itemModel.setPage(PageUtils.getPage(itemService.count(), limit));
        model.addAttribute("itemModel", itemModel);
        model.addAttribute("categories", categoryService.mapFindAll());
        return "views/admin/item/edit";
    }
}
