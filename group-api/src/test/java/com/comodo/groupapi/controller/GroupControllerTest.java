package com.comodo.groupapi.controller;

import com.comodo.groupapi.dto.CreateGroupDto;
import com.comodo.groupapi.dto.GroupDto;
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
class GroupControllerTest {

    @Autowired
    GroupController groupController;

    CreateGroupDto createGroupDto;

    @BeforeEach
    void setUp() {
        createGroupDto=new CreateGroupDto();
        createGroupDto.setUserId(1L);
        createGroupDto.setName("TEST_HOME");
    }

    @Test
    public void test_group_when_save(){
        ResponseEntity<GroupDto> userResponseEntity=groupController.save(createGroupDto);
        Assertions.assertTrue(userResponseEntity.getStatusCode().equals(HttpStatus.CREATED));
    }
}
