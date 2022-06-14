package com.skcc.tes.userservice.application.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.skcc.tes.userservice.domain.ports.api.UserServicePort;
import com.skcc.tes.userservice.domain.ports.spi.SocialServicePort;
import com.skcc.tes.userservice.infrastructure.entity.User;
import com.skcc.tes.userservice.domain.data.UserDto;
import com.skcc.tes.userservice.domain.data.SocialDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS})
@RequiredArgsConstructor
public class SocialController {

    private final SocialServicePort socialService;
    private final UserServicePort userService;
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users/login/{provider}")
    public ResponseEntity<UserDto> socialLogin(@PathVariable String provider,
                                               @RequestParam String code,
                                               HttpServletResponse response){
        User savedUser;
        SocialDto socialDto;

        if (provider.equals("kakao")) {
            socialDto = socialService.verification(code);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Optional<User> users = userService.findById(socialDto.getId());

        // 서비스에 등록된 회원이 아니라면
        if (users.isEmpty()) {
            User userEntity = User.builder()
                    .id(socialDto.getId())
                    .imageUrl(socialDto.getImageUrl())
                    .name(socialDto.getName())
                    .build();
            // 회원가입
            savedUser = userService.save(userEntity);
        } else {
            savedUser = users.get();
        }

        // JWT 토큰 생성
        String token = JWT.create()
                .withSubject("JwtToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000000000L))
                .withClaim("id", socialDto.getId())
                .sign(Algorithm.HMAC512("SECRET"));

        // JWT 토큰 헤더에 담아 전달
        response.addHeader("JWT_TOKEN", "PREFIX_" + token);

        return new ResponseEntity<>(savedUser.toDto(), HttpStatus.OK);
    }


    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDto> changeUser(@PathVariable Long userId, @RequestBody User user) {
        if (!Objects.equals(userId, user.getId())) {
            return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
        }
        Optional<User> byId = userService.findById(user.getId());
        if (byId.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
        }
        User saved = userService.save(user);
        return new ResponseEntity<>(saved.toDto(), HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
