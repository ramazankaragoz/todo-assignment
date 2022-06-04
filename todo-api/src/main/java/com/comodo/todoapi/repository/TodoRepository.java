package com.comodo.todoapi.repository;


import com.comodo.todoapi.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {

    boolean existsByGroupIdAndTodoName(Long groupId, String todoName);

    Todo getByGroupIdAndTodoName(Long groupId, String todoName);
}
