package com.vanhieu.service;

import com.vanhieu.dto.UserDto;

public interface IUserService {

    UserDto getUserByUsername(String username);
    UserDto save(UserDto userDto);
    void delete(Long[] ids);
    UserDto getOne(Long id);
}
