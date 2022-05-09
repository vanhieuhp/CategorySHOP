package com.vanhieu.api;

import com.vanhieu.dto.UserDto;
import com.vanhieu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAPI {

    @Autowired
    private IUserService userService;

    @PostMapping("/api/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        System.out.println("hello mvc");
        return userService.save(userDto);
    }

    @PutMapping("/api/user")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @DeleteMapping("/api/user")
    public void DeleteUser(@RequestBody Long[] ids) {
        userService.delete(ids);
    }
}
