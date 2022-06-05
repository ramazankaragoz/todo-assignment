package com.comodo.todoapi.repository;

import com.comodo.todoapi.entity.Todo;
import com.comodo.todoapi.util.PriorityEnum;
import com.comodo.todoapi.util.StatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoRepositoryTest {

    @Autowired
    TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        Todo todo=new Todo();
        todo.setTodoName("TEST_TODO_1");
        todo.setDueDate(new Date());
        todo.setPriority(PriorityEnum.NORMAL);
        todo.setStatus(StatusEnum.DONE);
        todo.setGroupId(1L);
        todoRepository.save(todo);
    }

    @Test
    void test_todo_when_exists_group_and_todo() {
        Assertions.assertTrue(todoRepository.existsByGroupIdAndTodoName(1L,"TEST_TODO_1"));
    }
}
