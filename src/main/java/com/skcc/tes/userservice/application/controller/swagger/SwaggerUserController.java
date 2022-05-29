package com.skcc.tes.userservice.application.controller.swagger;

import com.skcc.tes.userservice.application.controller.UserController;
import com.skcc.tes.userservice.domain.ports.api.UserServicePort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SwaggerUserController extends UserController {

    public SwaggerUserController(UserServicePort userService) {
        super(userService);
    }
}
