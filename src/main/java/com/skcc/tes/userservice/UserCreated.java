package com.skcc.tes.userservice;

import com.skcc.tes.userservice.AbstractEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreated extends AbstractEvent {

    private String userId;
    private String status;
}
