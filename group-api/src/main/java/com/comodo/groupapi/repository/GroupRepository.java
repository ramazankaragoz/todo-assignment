package com.comodo.groupapi.repository;

import com.comodo.groupapi.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {
    Group getById(Long id);
}