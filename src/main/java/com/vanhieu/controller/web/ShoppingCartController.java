package com.vanhieu.controller.web;

import com.vanhieu.dto.CartDto;
import com.vanhieu.dto.MyUser;
import com.vanhieu.dto.UserDto;
import com.vanhieu.service.ICartService;
import com.vanhieu.service.IUserService;
import com.vanhieu.util.ViewModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IUserService userService;

    @GetMapping("/WEBPAGE/shoppingCart")
    public String showShoppingCart(Model model) {
        List<CartDto> carts = cartService.findByUserid(ViewModelUtils.getUser().getId());
        Collections.reverse(carts);
        int sum = 0;
        for (CartDto cart : carts) {
            sum += cart.getTotal();
        }
        model.addAttribute("carts", carts);
        model.addAttribute("sum", sum);
        model.addAttribute("active", "idShopCart");
        return "views/web/shoppingCart";
    }
}
