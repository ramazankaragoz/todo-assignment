package com.comodo.todoassignmentgateway.controller;


import com.comodo.todoassignmentgateway.client.TodoClient;
import com.comodo.todoassignmentgateway.dto.CreateTodoDto;
import com.comodo.todoassignmentgateway.dto.TodoDto;
import com.comodo.todoassignmentgateway.dto.UpdateTodoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    private final TodoClient todoClient;

    public TodoController(TodoClient todoClient) {
        this.todoClient = todoClient;
    }

    @PostMapping
    @PreAuthorize("hasPermission('USER','WRITE')")
    public ResponseEntity<TodoDto> save(@Valid @RequestBody CreateTodoDto createTodoDto) {
        return todoClient.save(createTodoDto);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasPermission('USER','WRITE')")
    public ResponseEntity<TodoDto> update(@PathVariable Long id, @Valid @RequestBody UpdateTodoDto updateTodoDto) {
        return todoClient.update(id, updateTodoDto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasPermission('USER','DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
       return todoClient.delete(id);
    }

}
