package com.skcc.tes.userservice.dto;

import com.skcc.tes.userservice.enums.USER_TYPE;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id; // 사용자 고유 ID

    //    private String email;   // 받고 있지 않음
    private String name;    // 사용자 닉네임
    //    private String pwd;     // 사용하지 않음
//    private String userId;  // 사용하지 않음
    private String status;
    private String imageUrl;
    private String address; // 주소
    private USER_TYPE userType;

}
