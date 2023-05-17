package com.wakeup.user.domain.dto;

import lombok.Getter;

@Getter
public class TokenLoginRequest {
    String userName;
    String key;
    Long accessExpireTimeMs;
    Long refreshExpireTimeMs;

    public TokenLoginRequest(String userName, String key) {
        this.userName = userName;
        this.key = key;
        this.accessExpireTimeMs = 3600 * 1000l;
        this.refreshExpireTimeMs = 3600 * 24 * 1000l;
    }
}
