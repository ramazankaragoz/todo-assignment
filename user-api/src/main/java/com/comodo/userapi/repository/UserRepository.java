package com.comodo.userapi.repository;

import com.comodo.userapi.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Long> {

    AppUser getByEmail(String email);
}
