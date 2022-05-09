package com.vanhieu.service.impl;

import com.vanhieu.converter.CategoryConverter;
import com.vanhieu.dto.CategoryDto;
import com.vanhieu.entity.CategoryEntity;
import com.vanhieu.entity.ItemEntity;
import com.vanhieu.repository.CategoryRepository;
import com.vanhieu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public CategoryDto getOne(Long id) {
        return categoryConverter.toDto(categoryRepository.getById(id));
    }

    @Override
    public Map<String, String> mapFindAll() {
        Map<String, String> results = new HashMap<>();
        List<CategoryEntity> entities = (List<CategoryEntity>) categoryRepository.findAll();
        for (CategoryEntity item : entities) {
            results.put(item.getCode(), item.getName());
        }
        return results;
    }

    @Override
    public List<CategoryDto> findAll() {
        List<CategoryDto> results = new ArrayList<>();
        List<CategoryEntity> entities = (List<CategoryEntity>) categoryRepository.findAll();
        for (CategoryEntity item : entities) {
            results.add(categoryConverter.toDto(item));
        }
        return results;
    }

    @Override
    public int count() {
        return (int) categoryRepository.count();
    }

    @Override
    public CategoryDto findByCode(String code) {
        CategoryEntity entity = categoryRepository.getByCode(code);
        return categoryConverter.toDto(entity);
    }

    @Override
    @Transactional
    public CategoryDto save(CategoryDto dto) {
        CategoryEntity entity = new CategoryEntity();
        if (dto.getId() != null) {
            entity = categoryRepository.getById(dto.getId());
            entity = categoryConverter.toEntity(entity, dto);
        } else {
            entity = categoryConverter.toEntity(dto);
        }
        CategoryEntity item = categoryRepository.save(entity);
        return categoryConverter.toDto(item);
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for (Long id : ids) {
            categoryRepository.deleteById(id);
        }
    }
}
