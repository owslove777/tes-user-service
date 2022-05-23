package com.skcc.tes.userservice.application.config;

import com.skcc.tes.userservice.domain.ports.api.UserServiceImpl;
import com.skcc.tes.userservice.domain.ports.api.UserServicePort;
import com.skcc.tes.userservice.domain.ports.spi.UserPersistencePort;
import com.skcc.tes.userservice.infrastructure.adapters.jpa.UserJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserPersistencePort userPersistence(){
        return new UserJpaAdapter();
    }

    @Bean
    public UserServicePort userService(){
        return new UserServiceImpl(userPersistence());
    }
}
