package com.comodo.todoapi.service;

import com.comodo.todoapi.dto.CreateTodoDto;
import com.comodo.todoapi.dto.TodoDto;
import com.comodo.todoapi.dto.UpdateTodoDto;

public interface TodoService {

    TodoDto save(CreateTodoDto createTodoDto);

    TodoDto update(Long id, UpdateTodoDto updateTodoDto);

    void delete(Long id);
}
