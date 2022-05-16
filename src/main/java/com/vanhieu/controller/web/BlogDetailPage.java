package com.vanhieu.controller.web;

import com.vanhieu.dto.BlogDto;
import com.vanhieu.service.IBlogService;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BlogDetailPage {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/WEBPAGE/common/blogDetail/{id}")
    public String showBlogList(@PathVariable("id") Long id, Model model) {

        if (ViewModelUtils.getCategories()  == null) {
            ViewModelUtils.setCategories(categoryService.findAll());
        }
        if (ViewModelUtils.getRecentBlogs() == null) {
            ViewModelUtils.setRecentBlogs(blogService.getRecentBlogs(3));
        }
        if (id != null) {
            BlogDto blogModel = blogService.getOne(id);
            model.addAttribute("blogModel", blogModel);
            if (id >= blogService.count() - 3) {
                List<BlogDto> blogs = blogService.getRecentBlogs(4);
                for (int i = 0; i < blogs.size(); i++) {
                    if (blogs.get(i).getId() == id) {
                        blogs.remove(i);
                        break;
                    }
                }
                model.addAttribute("recentBlogs", blogs);
            } else {
                model.addAttribute("recentBlogs", ViewModelUtils.getRecentBlogs());
            }
        }
        model.addAttribute("categories", ViewModelUtils.getCategories());
        model.addAttribute("active", "idBlog");
        return "views/web/blogDetail";
    }
}