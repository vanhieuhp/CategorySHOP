package com.vanhieu.repository;

import com.vanhieu.entity.BlogEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

    @Query("SELECT b FROM BlogEntity b WHERE b.status = ?1")
    List<BlogEntity> findAll(int status);

    @Query("SELECT b FROM BlogEntity b WHERE b.status = ?1")
    List<BlogEntity> findAllByPageable(Pageable pageable, int status);
}
