package com.skcc.tes.userservice.domain.data;

import lombok.Data;

@Data
public class SocialDto {
    private Long id;
    private String email;
    private String name;
    private String imageUrl;
}
