package com.vanhieu.converter;

import com.vanhieu.dto.BillDto;
import com.vanhieu.dto.CartDto;
import com.vanhieu.entity.BillEntity;
import com.vanhieu.entity.CartEntity;
import com.vanhieu.entity.ItemEntity;
import com.vanhieu.entity.UserEntity;
import com.vanhieu.repository.BillRepository;
import com.vanhieu.repository.ItemRepository;
import com.vanhieu.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillConverter {

    ModelMapper mapper = new ModelMapper();

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    public BillDto toDto(BillEntity entity) {
        BillDto billDto = mapper.map(entity, BillDto.class);
        UserEntity user = entity.getUserOfBill();
        billDto.setUserId(user.getId());
        return billDto;
    }

    public BillEntity toEntity(BillDto billDto) {
        BillEntity billEntity = mapper.map(billDto, BillEntity.class);
        UserEntity user = userRepository.getOne(billDto.getUserId());
        billEntity.setUserOfBill(user);
        return billEntity;
    }
}
