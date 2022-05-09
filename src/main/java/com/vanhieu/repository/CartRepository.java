package com.vanhieu.repository;

import com.vanhieu.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    @Query("select c from CartEntity c where c.user.id = ?1 and c.status = ?2")
    List<CartEntity> findByUserid(Long id, int status);

    @Query("select c from CartEntity c where c.itemCart.id = ?1")
    List<CartEntity> findByItemId(Long id);

    @Query("SELECT c FROM CartEntity c WHERE c.status = ?1")
    List<CartEntity> findByStatus(int id);

}
