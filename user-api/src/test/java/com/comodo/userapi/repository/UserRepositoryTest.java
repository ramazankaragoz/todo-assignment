package com.comodo.userapi.repository;

import com.comodo.userapi.entity.AppUser;
import com.comodo.userapi.entity.Privilege;
import com.comodo.userapi.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        var appUser=new AppUser();
        appUser.setPassword("test");
        appUser.setEmail("appuser@app.com");
        appUser.setEnabled(Boolean.TRUE);

        var role=new Role();
        role.setRoleName("APP_ROLE");

        var privilege=new Privilege();
        privilege.setPrivilegeName("APP_PRIVILEGE");
        role.setPrivileges(List.of(privilege));

        appUser.setRoles(List.of(role));

        userRepository.save(appUser);
    }

    @Test
    void test_user_when_exists_email() {
        Assertions.assertTrue(userRepository.existsByEmail("appuser@app.com"));
    }
}
