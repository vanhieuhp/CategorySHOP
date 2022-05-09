package com.vanhieu.converter;

import com.vanhieu.dto.CartDto;
import com.vanhieu.entity.CartEntity;
import com.vanhieu.entity.ItemEntity;
import com.vanhieu.entity.UserEntity;
import com.vanhieu.repository.ItemRepository;
import com.vanhieu.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {

    ModelMapper mapper = new ModelMapper();

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemConverter itemConverter;

    public CartDto toDto(CartEntity entity) {
        CartDto cartDto = mapper.map(entity, CartDto.class);
        ItemEntity item = entity.getItemCart();
        UserEntity user = entity.getUser();
        cartDto.setTotal(item.getPrice() * cartDto.getQuantity());
        cartDto.setUserId(user.getId());
        cartDto.setItemDto(itemConverter.toDto(item));
        return cartDto;
    }

    public CartEntity toEntity(CartDto dto) {
        CartEntity entity = mapper.map(dto, CartEntity.class);
        ItemEntity item = itemRepository.getOne(dto.getItemId());
        UserEntity user = userRepository.getOne(dto.getUserId());
        entity.setItemCart(item);
        entity.setUser(user);
        return entity;
    }
}
