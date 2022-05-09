package com.vanhieu.service;

import com.vanhieu.dto.BillDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBillService {

    int count();
    BillDto save(BillDto billDto);
    void update(BillDto billDto);
    void delete(Long[] ids);
    List<BillDto> findByUserid(Long id);
    List<BillDto> findAll(Pageable pageable);
}
