package com.skcc.tes.userservice.infrastructure.adapters.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreated extends AbstractEvent {

    private String userId;
    private String userNm;
    private String status;
}
