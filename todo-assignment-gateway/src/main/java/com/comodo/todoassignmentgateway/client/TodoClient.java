package com.comodo.todoassignmentgateway.client;

import com.comodo.todoassignmentgateway.config.feign.FeignClientConfig;
import com.comodo.todoassignmentgateway.dto.CreateTodoDto;
import com.comodo.todoassignmentgateway.dto.TodoDto;
import com.comodo.todoassignmentgateway.dto.UpdateTodoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "todo-client", url = "http://localhost:8082/todo",configuration = {FeignClientConfig.class})
public interface TodoClient {

    @PostMapping
    ResponseEntity<TodoDto> save(@Valid @RequestBody CreateTodoDto createTodoDto);
    @PutMapping("{id}")
    ResponseEntity<TodoDto> update(@PathVariable Long id, @Valid @RequestBody UpdateTodoDto updateTodoDto);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}
