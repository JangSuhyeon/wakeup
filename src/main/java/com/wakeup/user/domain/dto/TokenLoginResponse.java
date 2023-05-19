package com.wakeup.user.domain.dto;

import com.wakeup.user.domain.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@AllArgsConstructor
public class TokenLoginResponse {
    String userName;
    String accessToken;
    String refreshToken;
    Date expireTime;

    public Token toEntity(String userName, String refreshToken, Date expireTime){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Token.builder()
                .userName(this.userName)
                .refreshToken(this.refreshToken)
                .expireTime(dateFormat.format(this.expireTime))
                .build();
    }
}
