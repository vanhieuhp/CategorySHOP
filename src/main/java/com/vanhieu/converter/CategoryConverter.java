package com.vanhieu.converter;

import com.vanhieu.dto.CategoryDto;
import com.vanhieu.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    ModelMapper mapper = new ModelMapper();

    public CategoryDto toDto(CategoryEntity entity) {
        CategoryDto dto = mapper.map(entity, CategoryDto.class);
        return dto;
    }

    public CategoryEntity toEntity(CategoryDto dto) {
        CategoryEntity entity = mapper.map(dto, CategoryEntity.class);
        return entity;
    }

    public CategoryEntity toEntity(CategoryEntity entity, CategoryDto dto) {
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        return entity;
    }
}
