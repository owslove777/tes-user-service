package com.skcc.tes.userservice.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.skcc.tes.userservice.domain.User;
import com.skcc.tes.userservice.domain.UserRepository;
import com.skcc.tes.userservice.oauth.SocialDto;
import com.skcc.tes.userservice.oauth.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class SocialController {

    private final SocialService socialService;
    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/users/login/{provider}")
    public ResponseEntity socialLogin(@PathVariable String provider,
                                      @RequestParam String code,
                                      HttpServletResponse response){

        SocialDto socialDto = null;

        if (provider.equals("kakao")) {
            socialDto = socialService.verificationKakao(code);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Optional<User> users = userRepository.findById(socialDto.getId());

        // 서비스에 등록된 회원이 아니라면
        if (users.isEmpty()) {
//            UserDto userDto = new UserDto();
//            userDto.setId(socialDto.getId());
//            userDto.setName(socialDto.getName());
//            userDto.setImageUrl(socialDto.getImageUrl());
            User userEntity = User.builder()
                    .id(socialDto.getId())
//                    .userType(userDto.getUserType())
                    .imageUrl(socialDto.getImageUrl())
                    .name(socialDto.getName())
//                    .status(userDto.getStatus())
//                    .address(userDto.getAddress())
                    .build();
            // 회원가입
            userRepository.save(userEntity);
        }

        // JWT 토큰 생성
        String token = JWT.create()
                .withSubject("JwtToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000000000L))
                .withClaim("id", socialDto.getId())
                .sign(Algorithm.HMAC512("SECRET"));

        // JWT 토큰 헤더에 담아 전달
        response.addHeader("JWT_TOKEN", "PREFIX_" + token);

        return new ResponseEntity(HttpStatus.OK);
    }
}
