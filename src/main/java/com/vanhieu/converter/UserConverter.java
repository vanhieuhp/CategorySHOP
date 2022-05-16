package com.vanhieu.converter;

import com.vanhieu.dto.RoleDto;
import com.vanhieu.dto.UserDto;
import com.vanhieu.entity.RoleEntity;
import com.vanhieu.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    ModelMapper mapper = new ModelMapper();

    public UserDto toDto(UserEntity entity) {
        UserDto userDto = new UserDto();
        userDto.setImage(entity.getImage());
        userDto.setUsername(entity.getUsername());
        userDto.setFullname(entity.getFullname());
        userDto.setPassword(entity.getPassword());
        userDto.setEmail(entity.getEmail());
        userDto.setStatus(entity.getStatus());
        userDto.setId(entity.getId());
        userDto.setRoleId(entity.getRoles().get(0).getId());
        List<RoleEntity> roles = entity.getRoles();
        List<RoleDto> roleDtos = new ArrayList<>();
        for (RoleEntity role : roles) {
            RoleDto roleDto = mapper.map(role, RoleDto.class);
            roleDtos.add(roleDto);
        }
        userDto.setRoles(roleDtos);
        return userDto;
    }

    public UserEntity toEntity(UserDto userDto) {
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        return userEntity;
    }
}
