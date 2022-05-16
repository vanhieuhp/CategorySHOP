package com.vanhieu.controller.admin;

import com.vanhieu.dto.BlogDto;
import com.vanhieu.service.IBlogService;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.util.PageUtils;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class BlogOfAdminController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/ADMIN/blog/list")
    public String showList(@RequestParam("page") int page, @RequestParam("limit") int limit,
                           HttpServletRequest request, Model model) {
        BlogDto blogModel = new BlogDto();
        Pageable pageable = PageRequest.of(page - 1, limit);
        blogModel.setListResult(blogService.findAllByPageable(pageable, 1));
        blogModel.setTotalItem(blogService.count());
        blogModel = PageUtils.getModel(blogModel, page, limit);
        ViewModelUtils.setMessage(request, model);
        model.addAttribute("blogModel", blogModel);
        return "views/admin/blog/list";
    }

    @GetMapping("/ADMIN/blog/edit")
    public String showEditPage(@RequestParam("limit") int limit,
                               HttpServletRequest request, Model model) {
        BlogDto blogModel = new BlogDto();
        if (request.getParameter("id") != null) {
            Long id = Long.valueOf(request.getParameter("id"));
            blogModel = blogService.getOne(id);
            model.addAttribute("pageTitle", "Edit item (ID: " + blogModel.getId() + ")");
        } else {
            model.addAttribute("pageTitle", "Add new item");
        }

        blogModel.setPage(PageUtils.getPage(blogService.count(), limit));
        model.addAttribute("blogModel", blogModel);
        model.addAttribute("categories", categoryService.findAll());
        return "views/admin/blog/edit";
    }
}
