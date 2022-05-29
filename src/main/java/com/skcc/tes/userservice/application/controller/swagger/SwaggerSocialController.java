package com.skcc.tes.userservice.application.controller.swagger;

import com.skcc.tes.userservice.application.controller.SocialController;
import com.skcc.tes.userservice.application.controller.UserController;
import com.skcc.tes.userservice.domain.ports.api.UserServicePort;
import com.skcc.tes.userservice.domain.ports.spi.SocialServicePort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class SwaggerSocialController extends SocialController {


    public SwaggerSocialController(SocialServicePort socialService, UserServicePort userService) {
        super(socialService, userService);
    }
}
