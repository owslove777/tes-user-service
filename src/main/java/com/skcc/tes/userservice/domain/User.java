package com.skcc.tes.userservice.domain;


import com.skcc.tes.userservice.UserCreated;
import com.skcc.tes.userservice.UserServiceApplication;
import com.skcc.tes.userservice.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;


@Entity(name = "tes_user")
@Table(name = "tes_user")
@Setter
@Getter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String email;
    private String name;
    private String pwd;
    private String userId;
    private String status;




    public void onPrePersist() {

        UserCreated userCreated = new UserCreated();
        userCreated.setStatus("valid");
        this.setStatus("valid");
        userCreated.setUserId(this.getUserId());
        BeanUtils.copyProperties(this,userCreated);
        userCreated.publishAfterCommit();

    }

    public UserDto toDto(){

        return UserDto.builder()
                        .email(email)
                        .name(name)
                        .pwd(pwd)
                        .userId(userId)
                        .status(status)
                        .build();
    }

    public UserDto save() {
            UserRepository repository = UserServiceApplication.getApplicationContext().getBean(UserRepository.class);
            User saved = repository.save(this);
            return saved.toDto();
    }

}


