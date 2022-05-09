package com.vanhieu.service;

import com.vanhieu.dto.ItemDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IItemService {
    List<ItemDto> findAll(Pageable pageable);
    List<ItemDto> findByCategoryCode(String categoryCode, int numOfItem);
    List<ItemDto> findByCategoryId(Long categoryId, Pageable pageable);
    int count();
    ItemDto save(ItemDto dto);
    void delete(Long[] ids);
    ItemDto getOne(Long id);
}
