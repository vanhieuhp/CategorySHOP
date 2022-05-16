package com.vanhieu.controller.web;

import com.vanhieu.dto.BlogDto;
import com.vanhieu.dto.CategoryDto;
import com.vanhieu.service.IBlogService;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BlogPage {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/WEBPAGE/common/blogPage")
    public String showBlogList(Model model) {
        if (ViewModelUtils.getCategories()  == null) {
            ViewModelUtils.setCategories(categoryService.findAll());
        }
        if (ViewModelUtils.getRecentBlogs() == null) {
            ViewModelUtils.setRecentBlogs(blogService.getRecentBlogs(3));
        }
        BlogDto blogModel = new BlogDto();
        blogModel.setPage(1);
        blogModel.setLimit(6);
        Pageable pageable = PageRequest.of(blogModel.getPage()-1, blogModel.getLimit());
        List<BlogDto> blogs = blogService.findAllByPageable(pageable, 1);
        blogModel.setListResult(blogs);
        blogModel.setTotalItem(blogService.count());
        blogModel.setTotalPage((int) Math.ceil( (double) blogModel.getTotalItem() / blogModel.getLimit()));
        model.addAttribute("categories", ViewModelUtils.getCategories());
        model.addAttribute("blogModel", blogModel);
        model.addAttribute("recentBlogs", ViewModelUtils.getRecentBlogs());
        model.addAttribute("active", "idBlog");
        return "views/web/blogPage";
    }
}
