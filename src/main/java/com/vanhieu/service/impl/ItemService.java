package com.vanhieu.service.impl;

import com.vanhieu.converter.ItemConverter;
import com.vanhieu.entity.CategoryEntity;
import com.vanhieu.repository.CategoryRepository;
import com.vanhieu.dto.ItemDto;
import com.vanhieu.entity.ItemEntity;
import com.vanhieu.repository.ItemRepository;
import com.vanhieu.service.ICategoryService;
import com.vanhieu.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemConverter itemConverter;

    @Override
    public List<ItemDto> findAll(Pageable pageable) {
        List<ItemDto> results = new ArrayList<>();
        List<ItemEntity> entities = (List<ItemEntity>) itemRepository.findAll(pageable).getContent();
        for (ItemEntity item : entities) {
            ItemDto dto = itemConverter.toDto(item);
            results.add(dto);
        }
        return results;
    }

    @Override
    public List<ItemDto> findByCategoryCode(String categoryCode, int numOfItem) {
        List<ItemEntity> itemEntities = itemRepository.findByCategoryCode(categoryCode);
        List<ItemDto> itemDtos = new ArrayList<>();
        for (int i = 0; i < itemEntities.size(); i ++) {
            if (i == numOfItem) break;
            itemDtos.add(itemConverter.toDto(itemEntities.get(i)));
        }
        return itemDtos;
    }

    @Override
    public List<ItemDto> findByCategoryId(Long categoryId, Pageable pageable) {
        List<ItemEntity> itemEntities = itemRepository.findByCategoryCode(categoryId, pageable);
        List<ItemDto> itemDtos = new ArrayList<>();
        for (int i = 0; i < itemEntities.size(); i ++) {
            itemDtos.add(itemConverter.toDto(itemEntities.get(i)));
        }
        return itemDtos;
    }

    @Override
    public int count() {
        return (int) itemRepository.count();
    }

    @Override
    @Transactional
    public ItemDto save(ItemDto dto) {
        CategoryEntity category = categoryRepository.getByCode(dto.getCategoryCode());
        ItemEntity entity = new ItemEntity();
        if (dto.getId() != null) {
            ItemEntity oldItem = itemRepository.getById(dto.getId());
            entity = itemConverter.toEntity(oldItem, dto);
            entity.setCategory(category);

        } else {
            entity = itemConverter.toEntity(dto);
            entity.setCategory(category);
        }
        ItemEntity item = itemRepository.save(entity);
        return itemConverter.toDto(item);
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for (Long id : ids) {
            itemRepository.deleteById(id);
        }
    }

    @Override
    public ItemDto getOne(Long id) {
        ItemEntity item = itemRepository.getById(id);
        CategoryEntity category = categoryRepository.getByCode(item.getCategory().getCode());
        item.setCategory(category);
        return itemConverter.toDto(item);
    }


}
