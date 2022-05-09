package com.vanhieu.api.admin;

import com.vanhieu.dto.ItemDto;
import com.vanhieu.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemAPIOfAdmin {

    @Autowired
    private IItemService itemService;

    @PostMapping("/api/item")
    public ItemDto createItem(@RequestBody ItemDto itemDto) {
        return itemService.save(itemDto);
    }

    @PutMapping("/api/item")
    public ItemDto updateItem(@RequestBody ItemDto itemDto){
        return itemService.save(itemDto);
    }

    @DeleteMapping("/api/item")
    public void deleteItem(@RequestBody Long[] ids){
        itemService.delete(ids);
    }

}
