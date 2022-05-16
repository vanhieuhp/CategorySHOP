package com.vanhieu.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AccountController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "views/account/login";
    }

    @GetMapping(value="/logout")
    public String showLogoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @GetMapping(value = "/register")
    public String showRegisterPage() {
        return "views/account/register";
    }

    @GetMapping(value = "/forgetPassword")
    public String showForgetPasswordPage() {
        return "views/account/forgetPassword";
    }

}
