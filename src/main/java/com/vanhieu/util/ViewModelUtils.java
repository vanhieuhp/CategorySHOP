package com.vanhieu.util;

import com.vanhieu.dto.BlogDto;
import com.vanhieu.dto.CategoryDto;
import com.vanhieu.dto.UserDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class ViewModelUtils {

    private static List<CategoryDto> categories = null;
    private static List<BlogDto> recentBlogs = null;
    private static UserDto user = null;

    public static void setCategories(List<CategoryDto> categoryDtos) {
        categories = categoryDtos;
    }

    public static void setRecentBlogs(List<BlogDto> blogDtos) {
        recentBlogs = blogDtos;
    }

    public static void setMessage(HttpServletRequest request, Model model) {
        if (request.getParameter("message") != null) {
            String message = request.getParameter("message");
            Map<String, String> messages = MessageUtils.getMessage(message);
            model.addAttribute("message", messages.get("message"));
            model.addAttribute("alert", messages.get("alert"));
        }
    }

    public static void setUser(UserDto userDto) {
        user = userDto;
    }

    public static UserDto getUser() {
        return user;
    }
    public static List<CategoryDto> getCategories() {
        return categories;
    }
    public static List<BlogDto> getRecentBlogs() {
        return recentBlogs;
    }

}
