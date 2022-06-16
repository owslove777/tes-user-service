package com.skcc.tes.userservice.infrastructure.entity;


import com.skcc.tes.userservice.UserServiceApplication;
import com.skcc.tes.userservice.domain.data.UserDto;
import com.skcc.tes.userservice.infrastructure.adapters.kafka.UserCreated;
import com.skcc.tes.userservice.infrastructure.repository.UserRepository;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import java.util.Collection;
import java.util.stream.Collectors;


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
        userCreated.setUserId(id);
        userCreated.setUserNm(name);
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
    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.id)
                .name(user.name)
                .status(user.status)
                .userType(user.userType)
                .imageUrl(user.imageUrl)
                .address(user.address)
                .email(user.email)
                .build();
    }

    public static Collection<UserDto> toDtoList(Collection<User> userIterable) {
        return userIterable.stream().map(u -> u.toDto()).collect(Collectors.toList());
    }

    public UserDto save() {
            UserRepository repository = UserServiceApplication.getApplicationContext().getBean(UserRepository.class);
            User saved = repository.save(this);
            return saved.toDto();
    }

}


