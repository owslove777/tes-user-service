package com.skcc.tes.userservice.controller;

import com.skcc.tes.userservice.domain.User;
import com.skcc.tes.userservice.domain.UserRepository;
import com.skcc.tes.userservice.dto.UserDto;
import com.skcc.tes.userservice.kafka.KafkaProcessor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

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
        Iterable<User> userList  = userRepository.findAll();

        List<UserDto> result = new ArrayList<>();

        userList.forEach(v -> result.add(new ModelMapper().map(v, UserDto.class)));

        return result;

    }

}
