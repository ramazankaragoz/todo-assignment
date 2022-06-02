package com.comodo.userapi.repository;

import com.comodo.userapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role getByRoleName(String roleName);
}
