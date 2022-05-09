package com.vanhieu.repository;

import com.vanhieu.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BillRepository extends JpaRepository<BillEntity, Long> {

    @Query("SELECT b FROM BillEntity b WHERE b.userOfBill.id = ?1")
    List<BillEntity> findByUserid(Long id);

}
