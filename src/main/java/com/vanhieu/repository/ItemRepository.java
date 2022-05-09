package com.vanhieu.repository;

import com.vanhieu.entity.ItemEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByCategoryCode(String categoryCode);

    @Query("select c from ItemEntity c where c.category.id = ?1")
    List<ItemEntity> findByCategoryCode(Long categoryId, Pageable pageable);
}
