package com.vanhieu.repository;

import com.vanhieu.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {


}
