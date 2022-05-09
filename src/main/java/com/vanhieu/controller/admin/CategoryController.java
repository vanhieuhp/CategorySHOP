package com.vanhieu.controller.admin;

import com.vanhieu.dto.CategoryDto;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CategoryController {

    @Autowired private ICategoryService categoryService;

    @GetMapping("/ADMIN/category/list")
    public String showList(Model model, HttpServletRequest request) {
        List<CategoryDto> categoryModel = categoryService.findAll();
        if (request.getParameter("message") != null) {
            String message = request.getParameter("message");
            Map<String, String> messages = MessageUtils.getMessage(message);
            model.addAttribute("message", messages.get("message"));
            model.addAttribute("alert", messages.get("alert"));
        }
        model.addAttribute("categoryModel", categoryModel);
        return "views/admin/category/list";
    }

    @GetMapping("/ADMIN/category/edit")
    public String showEditPage(HttpServletRequest request, Model model) {
        CategoryDto categoryModel = new CategoryDto();
        if (request.getParameter("id") != null) {
            Long id = Long.valueOf(request.getParameter("id"));
            categoryModel = categoryService.getOne(id);
            model.addAttribute("pageTitle", "Edit category (ID: " + categoryModel.getId() + ")");
        } else {
            model.addAttribute("pageTile", "Add category");
        }
        model.addAttribute("categoryModel", categoryModel);
        return "views/admin/category/edit";
    }
}
