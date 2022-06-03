package com.comodo.todoapi.service;

import com.comodo.todoapi.dto.CreateTodoDto;
import com.comodo.todoapi.dto.TodoDto;
import com.comodo.todoapi.dto.UpdateTodoDto;
import com.comodo.todoapi.entity.Todo;
import com.comodo.todoapi.exception.TodoAlReadyExistException;
import com.comodo.todoapi.exception.TodoNotFoundException;
import com.comodo.todoapi.mapper.TodoMapper;
import com.comodo.todoapi.repository.TodoRepository;
import com.comodo.todoapi.repository.search.SearchCriteria;
import com.comodo.todoapi.repository.search.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    @Override
    public TodoDto save(CreateTodoDto createTodoDto) {

        if (todoRepository.existsByGroupIdAndTodoName(createTodoDto.getGroupId(),createTodoDto.getTodoName())){
            throw new TodoAlReadyExistException("Todo must be belong to only one group.");
        }

        var todo = todoMapper.createDtotoEntity(createTodoDto);

        var savedTodo = todoRepository.save(todo);

        return todoMapper.toDto(savedTodo);
    }

    @Override
    public TodoDto update(Long id, UpdateTodoDto updateTodoDto) {
        var todo = todoRepository.getById(id);

        if (Objects.isNull(todo)){
            throw new TodoNotFoundException("Todo not found.");
        }

        var updatedTodo = todoMapper.update(todo, updateTodoDto);
        return todoMapper.toDto(todoRepository.save(updatedTodo));
    }

    @Override
    public void delete(Long id) {
        var todo = todoRepository.getById(id);

        if (Objects.isNull(todo)){
            throw new TodoNotFoundException("Todo not found.");
        }
        todoRepository.deleteById(id);
    }

    @Override
    public List<TodoDto> search(List<SearchCriteria> search) {
        Specification<Todo> specification = SpecificationBuilder
                .build(search);
        List<Todo> todoList = todoRepository.findAll(specification);
        return todoMapper.toDtoList(todoList);
    }
}
