package com.skcc.tes.userservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String email;
    private String name;
    private String pwd;
    private String userId;
    private String status;
    private String imageUrl;


}
