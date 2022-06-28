package com.skcc.tes.userservice.domain.ports.spi;

import com.skcc.tes.userservice.domain.data.SocialDto;

public interface SocialServicePort {
    SocialDto verification(String code, boolean isLocal);
}
