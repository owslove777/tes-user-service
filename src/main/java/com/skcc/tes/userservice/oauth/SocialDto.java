package com.skcc.tes.userservice.oauth;

import lombok.Data;

@Data
public class SocialDto {
    private Long id;
    private String email;
    private String name;
    private String imageUrl;
}
