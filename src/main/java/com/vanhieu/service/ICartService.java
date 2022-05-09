package com.vanhieu.service;

import com.vanhieu.dto.CartDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICartService {

    int count();
    CartDto save(CartDto cartDto);
    void update(CartDto cartDto);
    void delete(Long[] ids);
    List<CartDto> findByUserid(Long id);
    List<CartDto> findByItemId(Long id);
    List<CartDto> findAll(Pageable pageable);
    List<CartDto> findByStatus(int status);
    void payments (Long id);
}
