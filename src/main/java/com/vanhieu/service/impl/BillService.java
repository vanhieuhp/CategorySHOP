package com.vanhieu.service.impl;

import com.vanhieu.converter.BillConverter;
import com.vanhieu.dto.BillDto;
import com.vanhieu.dto.CartDto;
import com.vanhieu.entity.BillEntity;
import com.vanhieu.repository.BillRepository;
import com.vanhieu.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService implements IBillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillConverter billConverter;

    @Override
    public int count() {
        return (int) billRepository.count();
    }

    @Override
    @Transactional
    public BillDto save(BillDto billDto) {
        BillEntity bill = billConverter.toEntity(billDto);
        bill = billRepository.save(bill);
        return billConverter.toDto(bill);
    }

    @Override
    @Transactional
    public void update(BillDto billDto) {

    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for (Long id : ids) {
            billRepository.deleteById(id);
        }
    }

    @Override
    public List<BillDto> findByUserid(Long id) {
        List<BillEntity> bills = billRepository.findByUserid(id);
        List<BillDto> results = new ArrayList<>();
        for (BillEntity bill : bills) {
            results.add(billConverter.toDto(bill));
        }
        return results;
    }

    @Override
    public List<BillDto> findAll(Pageable pageable) {
        List<BillEntity> bills = billRepository.findAll(pageable).getContent();
        List<BillDto> results = new ArrayList<>();
        for (BillEntity bill : bills) {
            results.add(billConverter.toDto(bill));
        }
        return results;
    }
}
