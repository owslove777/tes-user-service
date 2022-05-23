package com.skcc.tes.userservice.domain.data;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id; // 사용자 고유 ID

    private String email;
    private String name;    // 사용자 닉네임
    //    private String pwd;     // 사용하지 않음
//    private String userId;  // 사용하지 않음
    private String status;
    private String imageUrl;
    private String address; // 주소
//    private USER_TYPE userType;
    private String userType;
}
