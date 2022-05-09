package com.vanhieu.api.web;

import com.vanhieu.dto.CartDto;
import com.vanhieu.service.ICartService;
import com.vanhieu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartApi {

    @Autowired
    private ICartService cartService;

    @Autowired
    private IUserService userService;

    @PostMapping("/api/cart")
    public CartDto createCart(@RequestBody CartDto cartDto) {
        cartDto.setUserId(userService.getUserByUsername(cartDto.getUsername()).getId());
        cartDto.setStatus(1);
        return cartService.save(cartDto);
    }

    @DeleteMapping("/api/cart")
    public void deleteCart(@RequestBody Long[] ids) {
        cartService.delete(ids);
    }

    @PutMapping("/api/cart")
    public void updateCart(@RequestBody CartDto cartDto) {
        cartService.update(cartDto);
    }
}
