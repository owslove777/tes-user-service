package com.skcc.tes.userservice;

import com.skcc.tes.userservice.AbstractEvent;
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
    private String status;
}
