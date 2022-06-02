package com.comodo.todoapi.controller;

import com.comodo.todoapi.dto.CreateTodoDto;
import com.comodo.todoapi.dto.TodoDto;
import com.comodo.todoapi.dto.UpdateTodoDto;
import com.comodo.todoapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoDto> save(@Valid @RequestBody CreateTodoDto createTodoDto) {
        return new ResponseEntity<>(todoService.save(createTodoDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDto> update(@PathVariable Long id, @Valid @RequestBody UpdateTodoDto updateTodoDto) {
        return new ResponseEntity<>(todoService.update(id, updateTodoDto),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
