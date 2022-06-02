package com.comodo.todoapi.repository;


import com.comodo.todoapi.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Todo getById(Long id);
    boolean existsByIdAndDeletedIsTrue(Long id);
}
