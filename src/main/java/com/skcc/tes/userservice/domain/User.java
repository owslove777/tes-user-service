package com.skcc.tes.userservice.domain;


import com.skcc.tes.userservice.UserCreated;
import com.skcc.tes.userservice.UserServiceApplication;
import com.skcc.tes.userservice.dto.UserDto;
import lombok.*;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;


@Entity(name = "tes_user")
@Table(name = "tes_user")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 사용자 고유 ID
    private String name;    // 사용자 닉네임
    private String status;
    private String imageUrl;
    private String address; // 주소
//    private USER_TYPE userType;
    private String userType;
    private String email;


    @PostPersist
    public void onPostPersist() {

        UserCreated userCreated = new UserCreated();
        userCreated.setStatus("valid");
        this.setStatus("valid");
//        userCreated.setUserId(this.getUserId());
        BeanUtils.copyProperties(this,userCreated);
        userCreated.publishAfterCommit();
        System.out.println("sending data=" + userCreated);

    }

    public UserDto toDto(){

        return UserDto.builder()
                .id(id)
                .name(name)
                .status(status)
                .userType(userType)
                .imageUrl(imageUrl)
                .address(address)
                .email(email)
                .build();
    }

    public UserDto save() {
            UserRepository repository = UserServiceApplication.getApplicationContext().getBean(UserRepository.class);
            User saved = repository.save(this);
            return saved.toDto();
    }

}


