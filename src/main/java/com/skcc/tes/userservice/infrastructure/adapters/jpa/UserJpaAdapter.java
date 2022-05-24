package com.skcc.tes.userservice.infrastructure.adapters.jpa;

import com.skcc.tes.userservice.domain.data.UserDto;
import com.skcc.tes.userservice.domain.ports.spi.UserPersistencePort;
import com.skcc.tes.userservice.infrastructure.entity.User;
import com.skcc.tes.userservice.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserJpaAdapter implements UserPersistencePort {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<UserDto> findAll() {
        List<User> all = userRepository.findAll();
//        return UserMapper.INSTANCE.userListToUserDtoList(all);
        return User.toDtoList(all);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }
}
