package com.vanhieu.service;

import com.vanhieu.dto.CategoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICategoryService {

    CategoryDto getOne(Long id);
    Map<String, String> mapFindAll();
    List<CategoryDto> findAll();
    int count();
    CategoryDto findByCode(String categoryCode);
    CategoryDto save(CategoryDto dto);
    void delete(Long[] ids);
}
