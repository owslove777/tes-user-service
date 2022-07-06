package com.skcc.tes.userservice.application.controller;

import com.skcc.tes.userservice.domain.ports.api.UserServicePort;
import com.skcc.tes.userservice.infrastructure.entity.User;
import com.skcc.tes.userservice.infrastructure.repository.UserRepository;
import com.skcc.tes.userservice.domain.data.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
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

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable Long id){
        Optional<User> byId = userService.findById(id);
        if (byId.isEmpty()){
            return null;
        }
        return byId.get().toDto();
    }
}
