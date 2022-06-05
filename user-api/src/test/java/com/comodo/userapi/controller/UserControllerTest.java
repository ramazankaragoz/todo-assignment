package com.comodo.userapi.controller;

import com.comodo.userapi.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController userController;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto=new UserDto();
        userDto.setPassword("123123");
        userDto.setEmail("ramazan@gmail.com");
        userDto.setEnabled(Boolean.TRUE);
    }

    @Test
    public void test_user_when_save(){
        ResponseEntity<String> userResponseEntity=userController.save(userDto);
        Assertions.assertTrue(userResponseEntity.getStatusCode().equals(HttpStatus.CREATED));
    }
}
