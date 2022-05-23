package com.skcc.tes.userservice.domain.ports.api;

import com.skcc.tes.userservice.domain.data.UserDto;
import com.skcc.tes.userservice.infrastructure.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServicePort {
    List<UserDto> getAllUsers();

    Optional<User> findById(Long id);

    User save(User userEntity);

    void deleteById(Long userId);
}
