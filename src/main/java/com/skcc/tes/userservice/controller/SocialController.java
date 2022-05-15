package com.skcc.tes.userservice.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.skcc.tes.userservice.domain.User;
import com.skcc.tes.userservice.domain.UserRepository;
import com.skcc.tes.userservice.dto.UserDto;
import com.skcc.tes.userservice.oauth.SocialDto;
import com.skcc.tes.userservice.oauth.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SocialController {

    private final SocialService socialService;
    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    @PostMapping("/users/login/{provider}")
    public ResponseEntity socialLogin(@PathVariable String provider,
                                      @RequestBody(required = false) UserDto userDto,
                                      @RequestParam String code,
                                      HttpServletResponse response){

        SocialDto socialDto = null;

        if (provider.equals("kakao")) {
            socialDto = socialService.verificationKakao(code);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<User> users = userRepository.findByEmail(socialDto.getEmail());

        // 서비스에 등록된 회원이 아니라면
        if (users.isEmpty()) {
            userDto.setEmail(socialDto.getEmail());
            userDto.setName(socialDto.getName());
            userDto.setImageUrl(socialDto.getImageUrl());
            userDto.setPwd(UUID.randomUUID().toString());
            User userEntity = User.builder()
                    .email(userDto.getEmail())
                    .pwd(userDto.getPwd())
                    .userId(userDto.getUserId())
                    .imageUrl(userDto.getImageUrl())
                    .build();
            // 회원가입
            userRepository.save(userEntity);
        }

        // JWT 토큰 생성
        String token = JWT.create()
                .withSubject("JwtToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("tes.token.expiration_time"))))
                .withClaim("email", socialDto.getEmail())
                .sign(Algorithm.HMAC512(env.getProperty("tes.token.secret")));

        // JWT 토큰 헤더에 담아 전달
        response.addHeader(env.getProperty("tes.token.header"), env.getProperty("tes.token.prefix") + token);

        return new ResponseEntity(HttpStatus.OK);
    }
}
