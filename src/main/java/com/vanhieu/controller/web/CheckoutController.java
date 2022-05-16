package com.vanhieu.controller.web;

import com.vanhieu.dto.CartDto;
import com.vanhieu.dto.CategoryDto;
import com.vanhieu.dto.MyUser;
import com.vanhieu.dto.UserDto;
import com.vanhieu.service.ICartService;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.service.IUserService;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IUserService userService;

    @GetMapping("/WEBPAGE/checkout")
    public String CheckoutPage(Model model) {
        model.addAttribute("categories", ViewModelUtils.getCategories());
        List<CartDto> carts = cartService.findByUserid(ViewModelUtils.getUser().getId());
        int sum = 0;
        for (CartDto cart : carts) {
            sum += cart.getTotal();
        }

        model.addAttribute("carts", carts);
        model.addAttribute("sum", sum);
        return "views/web/checkout";
    }
}
