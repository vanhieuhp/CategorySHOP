package com.vanhieu.converter;

import com.vanhieu.dto.ItemDto;
import com.vanhieu.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemConverter {

    ModelMapper mapper = new ModelMapper();

    public ItemDto toDto(ItemEntity entity) {
        ItemDto dto = mapper.map(entity, ItemDto.class);
        dto.setCategoryCode(entity.getCategory().getCode());
        dto.setCreatedBy(entity.getCreateBy());
        dto.setCreatedDate(entity.getCreateDate());
            return dto;
    }

    public ItemEntity toEntity(ItemDto dto) {
        ItemEntity entity = mapper.map(dto, ItemEntity.class);
        return entity;
    }

    public ItemEntity toEntity(ItemEntity oldEntity, ItemDto dto) {
        oldEntity.setImage(dto.getImage());
        oldEntity.setName(dto.getName());
        oldEntity.setPrice(dto.getPrice());
        return oldEntity;
    }

}
