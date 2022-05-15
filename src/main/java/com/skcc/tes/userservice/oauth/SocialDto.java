package com.skcc.tes.userservice.oauth;

import lombok.Data;

@Data
public class SocialDto {
    private String email;
    private String name;
    private String imageUrl;
}
