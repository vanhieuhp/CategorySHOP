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
    public String createUser(@RequestBody UserDto userDto) {
        String result = "";
        if (userService.getUserByUsername(userDto.getUsername()) != null) {
            result = "1";
        } else if (userService.getUserByEmail(userDto.getEmail()) != null) {
            result = "2";
        } else {
            userService.save(userDto);
            result = "3";
        }
        return result;
    }

    @PutMapping("/api/user")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }

    @DeleteMapping("/api/user")
    public void DeleteUser(@RequestBody Long[] ids) {
        userService.delete(ids);
    }

    @PutMapping("/api/forgetPassword")
    public String changPassword(@RequestBody UserDto userDto) {
        StringBuilder result = new StringBuilder("");
        if (userService.getUserByUsernameAndEmail(userDto.getUsername(), userDto.getEmail()) != null) {
            userService.changePassword(userDto, userDto.getPassword());
            result.append("Your password: " + userDto.getPassword());
        } else {
            result.append("false");
        }
        return  result.toString();
    }
}
