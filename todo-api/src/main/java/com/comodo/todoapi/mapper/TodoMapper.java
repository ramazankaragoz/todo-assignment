package com.comodo.todoapi.mapper;


import com.comodo.todoapi.dto.CreateTodoDto;
import com.comodo.todoapi.dto.TodoDto;
import com.comodo.todoapi.dto.UpdateTodoDto;
import com.comodo.todoapi.entity.Todo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TodoMapper {

    Todo toEntity(TodoDto dto);

    Todo createDtotoEntity(CreateTodoDto createTodoDto);

    TodoDto toDto(Todo todo);

    List<Todo> toEntityList(List<TodoDto> todoDtoList);

    List<TodoDto> toDtoList(List<Todo> todoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Todo update(@MappingTarget Todo todo, UpdateTodoDto updateTodoDto);

}
