package com.vanhieu.demospring;

import com.vanhieu.entity.UserEntity;
import com.vanhieu.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TestAPI {

    @Autowired private UserRepository repository;

    @Test
    public void testAndNew() {
        UserEntity user = new UserEntity();
        user.setEmail("admin.java@gmail.com");
        user.setPassword("javaweb");
        user.setUsername("admin");
        user.setFullname("admin");
        user.setStatus(1);

        UserEntity saveUser = repository.save(user);
        Assertions.assertThat(saveUser).isNotNull();
        Assertions.assertThat(saveUser.getId()).isGreaterThan(0);
    }

}
