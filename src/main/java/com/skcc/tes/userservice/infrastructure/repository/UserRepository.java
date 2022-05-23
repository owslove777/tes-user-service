package com.skcc.tes.userservice.infrastructure.repository;

import com.skcc.tes.userservice.infrastructure.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{

    List<User> findByEmail(String email);
}
