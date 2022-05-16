package com.vanhieu.api.admin;

import com.vanhieu.dto.BlogDto;
import com.vanhieu.dto.UserDto;
import com.vanhieu.service.IBlogService;
import com.vanhieu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class BlogApi {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private IUserService userService;

    @PostMapping("/api/blog")
    public BlogDto createBlog(@RequestBody BlogDto blogDto) {
        UserDto user = userService.getUserByUsername(blogDto.getUsername());
        blogDto.setAuthorId(user.getId());
        return blogService.save(blogDto);
    }

    @PutMapping("/api/blog")
    public BlogDto updateBlog(@RequestBody BlogDto blogDto) {
        UserDto user = userService.getUserByUsername(blogDto.getUsername());
        blogDto.setAuthorId(user.getId());
        return blogService.save(blogDto);
    }

    @DeleteMapping("/api/blog")
    public void deleteBlog(@RequestBody Long[] ids) {
        blogService.delete(ids);
    }

    @GetMapping("/api/blog")
    public String getBlog(HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        StringBuilder results = new StringBuilder("<div class=\"row\" id=\"blog\">");
        Pageable pageable = PageRequest.of(page-1, 6);
        List<BlogDto> blogs = blogService.findAllByPageable(pageable, 1);
        for (BlogDto blog : blogs) {
            String s = "<div class=\"col-lg-6 col-md-6 col-sm-6\">\n" +
                    "                            <div class=\"blog__item\">\n" +
                    "                                <div class=\"blog__item__pic\">\n" +
                    "                                    <img src=\"/web/img/blog/" + blog.getCategory().getCode() + "/" + blog.getImage() + ".jpg\" alt=\"\">\n" +
                    "                                </div>\n" +
                    "                                <div class=\"blog__item__text\">\n" +
                    "                                    <ul>\n" +
                    "                                        <li><i class=\"fa fa-calendar-o\"></i> " + blog.getCreatedDate() + "</li>\n" +
                    "                                        <li><i class=\"fa fa-comment-o\"></i> 5</li>\n" +
                    "                                    </ul>\n" +
                    "                                    <h5><a>" + blog.getTitle() + "</a></h5>\n" +
                    "                                    <p class=\"text-3line blog_text_content\" >" + blog.getShortDescription() + "</p>\n" +
                    "                                    <a href=\"/WEBPAGE/blogDetails/" + blog.getId() + "\" class=\"blog__btn\">READ MORE <span class=\"arrow_right\"></span></a>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>";
            results.append(s);
        }
        results.append("</div>");
        return results.toString();
    }
}
