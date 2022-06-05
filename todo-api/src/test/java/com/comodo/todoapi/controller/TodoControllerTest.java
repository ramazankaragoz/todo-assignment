package com.comodo.todoapi.controller;


import com.comodo.todoapi.dto.CreateTodoDto;
import com.comodo.todoapi.dto.TodoDto;
import com.comodo.todoapi.util.PriorityEnum;
import com.comodo.todoapi.util.StatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
public class TodoControllerTest {

    @Autowired
    private TodoController todoController;

    private CreateTodoDto createTodoDto;

    @Container
    public static RabbitMQContainer rabbitMQ = new RabbitMQContainer(DockerImageName.parse("rabbitmq:management"))
            .withExposedPorts(5672);

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", ()->rabbitMQ.getHost());
        registry.add("spring.rabbitmq.username", ()->rabbitMQ.getAdminUsername());
        registry.add("spring.rabbitmq.password", ()->rabbitMQ.getAdminPassword());
        registry.add("spring.rabbitmq.port", ()->rabbitMQ.getAmqpPort());
    }

    static {
        rabbitMQ.start();
    }

    @BeforeEach
    void setUp() {
        createTodoDto=new CreateTodoDto();
        createTodoDto.setTodoName("TEST_TODO");
        createTodoDto.setDueDate(new Date());
        createTodoDto.setGroupId(1L);
        createTodoDto.setPriority(PriorityEnum.HIGH);
        createTodoDto.setStatus(StatusEnum.DONE);
    }

    @Test
    public void test_todo_when_save(){
        ResponseEntity<TodoDto> customerResponse=todoController.save(createTodoDto);
        Assertions.assertTrue(customerResponse.getStatusCode().equals(HttpStatus.CREATED));

    }
}
