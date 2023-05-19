package com.wakeup.configuration;

import com.wakeup.user.domain.dto.TokenLoginRequest;
import com.wakeup.user.domain.dto.TokenLoginResponse;
import com.wakeup.user.repository.TokenRepository;
import com.wakeup.user.repository.UserRepository;
import com.wakeup.user.service.UserService;
import com.wakeup.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final TokenRepository tokenRepository;

    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        String cookieToken = "";
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    cookieToken = cookie.getValue();
                    cookieToken = UriUtils.decode(cookieToken, StandardCharsets.UTF_8);
                }
            }
        }
        final String authentication = cookieToken;

        // Token 유효성 검사
        if (authentication == null || !authentication.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        // Token 꺼내기
        String token = authentication.split(" ")[1];

        // JWT Expired 여부
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        String userName = claims.get("userName", String.class);

        int refreshTokenCheck = tokenRepository.countAllByUserName(userName);
        if(refreshTokenCheck == 0){
            TokenLoginResponse tokenLoginResponse = JwtTokenUtil.createToken(new TokenLoginRequest(userName, secretKey));
            String newToken = tokenLoginResponse.getAccessToken();
            System.out.println("새로운 토큰 발급 : " + newToken);
        }

        // Token Expired 여부
        if(JwtTokenUtil.isExpired(token, secretKey)){
            log.error("Token이 만료 되었습니다.");
            filterChain.doFilter(request, response);
            return;
        };

        // UserName Token에서 꺼내기
//        String userName = JwtTokenUtil.getUserName(token,secretKey);

        // 권한부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, null, List.of(new SimpleGrantedAuthority("USER")));

        // Detail을 넣어줍니다.
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
