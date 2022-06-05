package com.comodo.todoassignmentgateway.controller;


import com.comodo.todoassignmentgateway.client.TodoClient;
import com.comodo.todoassignmentgateway.dto.CreateTodoDto;
import com.comodo.todoassignmentgateway.dto.SearchCriteria;
import com.comodo.todoassignmentgateway.dto.TodoDto;
import com.comodo.todoassignmentgateway.dto.UpdateTodoDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    private final TodoClient todoClient;

    public TodoController(TodoClient todoClient) {
        this.todoClient = todoClient;
    }

    @Operation(summary = "Save a new todo to the system")
    @PostMapping
    @PreAuthorize("hasPermission('USER','WRITE')")
    public ResponseEntity<TodoDto> save(@Valid @RequestBody CreateTodoDto createTodoDto) {
        return todoClient.save(createTodoDto);
    }

    @Operation(summary = "Partial update a todo to the system")
    @PutMapping("{id}")
    @PreAuthorize("hasPermission('USER','WRITE')")
    public ResponseEntity<TodoDto> update(@PathVariable Long id, @Valid @RequestBody UpdateTodoDto updateTodoDto) {
        return todoClient.update(id, updateTodoDto);
    }

    @Operation(summary = "Performs data filtering")
    @PostMapping("/search")
    @PreAuthorize("hasPermission('USER','READ')")
    public ResponseEntity<List<TodoDto>> search(@RequestBody List<SearchCriteria> search){
        return todoClient.search(search);
    }

    @Operation(summary = "Delete a todo")
    @DeleteMapping("{id}")
    @PreAuthorize("hasPermission('USER','DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
       return todoClient.delete(id);
    }

}
