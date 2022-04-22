package com.demo.userservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<User, Long>{
    
}
