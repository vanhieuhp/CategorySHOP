package com.vanhieu.api.admin;

import com.vanhieu.dto.CategoryDto;
import com.vanhieu.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryAPIOfAdmin {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/api/category")
    public CategoryDto createItem(@RequestBody CategoryDto dto) {
        return categoryService.save(dto);
    }

    @PutMapping("/api/category")
    public CategoryDto updateItem(@RequestBody CategoryDto dto){
        return categoryService.save(dto);
    }

    @DeleteMapping("/api/category")
    public void deleteItem(@RequestBody Long[] ids){
        categoryService.delete(ids);
    }

}
