package com.skcc.tes.userservice.application.controller;

import com.skcc.tes.userservice.domain.ports.api.UserServicePort;
import com.skcc.tes.userservice.infrastructure.entity.User;
import com.skcc.tes.userservice.infrastructure.repository.UserRepository;
import com.skcc.tes.userservice.domain.data.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServicePort userService;
//    private final UserRepository userRepository;

    @GetMapping("/health_check")
    public String status() {
        return "It's Working in User Service";
    }


    @PostMapping("/user")
    public UserDto createUser(@RequestBody User src){
        
//        src.setUserId(UUID.randomUUID().toString());  //사용자ID 생성 // 자동 생성됩니다.
        return src.save();
    }

    @GetMapping("/users")
    public List<UserDto> getUsers(){
        return userService.getAllUsers();
    }

}
