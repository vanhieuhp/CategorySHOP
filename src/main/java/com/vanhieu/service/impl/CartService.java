package com.vanhieu.service.impl;

import com.vanhieu.converter.CartConverter;
import com.vanhieu.converter.UserConverter;
import com.vanhieu.dto.CartDto;
import com.vanhieu.entity.CartEntity;
import com.vanhieu.repository.CartRepository;
import com.vanhieu.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartConverter cartConverter;

    @Autowired
    private UserConverter userConverter;

    @Override
    public int count() {
        return (int) cartRepository.count();
    }

    @Override
    @Transactional
    public CartDto save(CartDto cartDto) {
        CartEntity cartEntity = cartConverter.toEntity(cartDto);
        cartEntity = cartRepository.save(cartEntity);
        return cartConverter.toDto(cartEntity);
    }

    @Override
    @Transactional
    public void update(CartDto cartDto) {
        CartEntity cartEntity = cartRepository.getOne(cartDto.getId());
        cartEntity.setQuantity(cartDto.getQuantity());
        cartRepository.save(cartEntity);
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for (Long id : ids) {
            CartEntity cartEntity = cartRepository.getOne(id);
            cartEntity.setStatus(0);
            cartRepository.save(cartEntity);
        }
    }

    @Override
    public List<CartDto> findByUserid(Long id) {
        List<CartEntity> entities = cartRepository.findByUserid(id, 1);
        List<CartDto> dtos = new ArrayList<>();
        for (CartEntity entity : entities) {
            dtos.add(cartConverter.toDto(entity));
        }
        return dtos;
    }

    @Override
    public List<CartDto> findByItemId(Long id) {
        return null;
    }

    @Override
    public List<CartDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<CartDto> findByStatus(int status) {
        List<CartEntity> entities = cartRepository.findByStatus(status);
        List<CartDto> dtos = new ArrayList<>();
        for (CartEntity entity : entities) {
            dtos.add(cartConverter.toDto(entity));
        }
        return dtos;
    }

    @Override
    @Transactional
    public void payments(Long id) {
        List<CartEntity> carts = cartRepository.findByUserid(id, 1);
        for (CartEntity cart : carts) {
            cart.setStatus(3);
        }
    }

}
