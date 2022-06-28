package com.skcc.tes.userservice.infrastructure.adapters.oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skcc.tes.userservice.domain.data.SocialDto;
import com.skcc.tes.userservice.domain.ports.spi.SocialServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoSocialServiceAdapter implements SocialServicePort {

    private final KakaoService kakaoService;
    private final ObjectMapper objectMapper;

    @Override
    public SocialDto verification(String code, boolean isLocal){

        SocialDto socialDto = new SocialDto();
        // 코드를 이용하여 accessToken 추출
        String accessToken = kakaoService.getAccessTokenByCode(code, isLocal);
        // accessToken을 이용하여 사용자 정보 추출
        String userInfo = kakaoService.getUserInfoByAccessToken(accessToken);

        try {
            JsonNode jsonNode = objectMapper.readTree(userInfo);
            String email = String.valueOf(jsonNode.get("kakao_account").get("email"));
            socialDto.setEmail("kakao_" + email.substring(1, email.length() - 1));
            String name = String.valueOf(jsonNode.get("kakao_account").get("profile").get("nickname"));
            socialDto.setName(name.substring(1, name.length() - 1));
            String imageUrl = String.valueOf(jsonNode.get("kakao_account").get("profile").get("profile_image_url"));
            socialDto.setImageUrl(imageUrl.substring(1, imageUrl.length() - 1));
            Long id = jsonNode.get("id").asLong();
            socialDto.setId(id); // 2245194061
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return socialDto;
    }
}
