package com.wakeup.utils;

import com.wakeup.user.domain.dto.TokenLoginRequest;
import com.wakeup.user.domain.dto.TokenLoginResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtTokenUtil {

    public static TokenLoginResponse createToken(TokenLoginRequest tokenReq) {
        String userName = tokenReq.getUserName();
        String key = tokenReq.getKey();
        long accessExpireTimeMs = tokenReq.getAccessExpireTimeMs();
        long refreshExpireTimeMs = tokenReq.getRefreshExpireTimeMs();

        Claims claims = Jwts.claims();
        claims.put("userName", userName);

        // Access Token 발급
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + accessExpireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        // Refresh Token 발급
        String refreshToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpireTimeMs))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        return new TokenLoginResponse(userName, accessToken, refreshToken, refreshExpireTimeMs);
    }

    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    public static String getUserName(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("userName",String.class);
    }

}
