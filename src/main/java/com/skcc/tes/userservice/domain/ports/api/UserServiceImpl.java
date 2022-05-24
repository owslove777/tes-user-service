package com.skcc.tes.userservice.domain.ports.api;

import com.skcc.tes.userservice.domain.data.UserDto;
import com.skcc.tes.userservice.domain.ports.spi.UserPersistencePort;
import com.skcc.tes.userservice.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

//@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServicePort{
    private final UserPersistencePort userPersistencePort;

    @Override
    public List<UserDto> getAllUsers() {
        Iterable<UserDto> userList  = userPersistencePort.findAll();
        return (List<UserDto>) userList;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userPersistencePort.findById(id);
    }

    @Override
    public User save(User userEntity) {
        return userPersistencePort.save(userEntity);
    }

    @Override
    public void deleteById(Long userId) {
        userPersistencePort.deleteById(userId);
    }
}
