package com.skcc.tes.userservice.domain.ports.spi;

import com.skcc.tes.userservice.domain.data.UserDto;
import com.skcc.tes.userservice.infrastructure.entity.User;

import java.util.Optional;

public interface UserPersistencePort {
    Iterable<UserDto> findAll();

    Optional<User> findById(Long id);

    User save(User userEntity);

    void deleteById(Long userId);
}
