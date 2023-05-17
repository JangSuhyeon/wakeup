package com.wakeup.user.domain.dto;

import com.wakeup.user.domain.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class TokenLoginResponse {
    String userName;
    String accessToken;
    String refreshToken;
    Long expireTime;

    public Token toEntity(String userName, String refreshToken, Long expireTime){
        return Token.builder()
                .userName(this.userName)
                .refreshToken(this.refreshToken)
                .expireTime(this.expireTime)
                .build();
    }
}
