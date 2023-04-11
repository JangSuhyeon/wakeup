package com.wakeup.user.service;

import com.wakeup.exception.AppException;
import com.wakeup.exception.ErrorCode;
import com.wakeup.user.domain.Role;
import com.wakeup.user.domain.User;
import com.wakeup.user.domain.dto.UserJoinResponse;
import com.wakeup.user.repository.UserRepository;
import com.wakeup.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;
    private long expireTimeMs = 1000 * 60 * 60l;

    public UserJoinResponse join(String userName, String password, String name, String email){

        // id중복확인
        userRepository.findByUserName(userName)
                .ifPresent(user -> {
                    throw new AppException(ErrorCode.USERNAME_DUPLICATED,"이미 가입된 아이디입니다.");
                });

        // 저장
        User user = User.builder()
                .userName(userName)
                .password(encoder.encode(password))
                .name(name)
                .email(email)
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return UserJoinResponse.builder()
                .userName(userName)
                .name(name)
                .email(email)
                .build();
    }

    public String login(String userName, String password) {

        // 아이디 없음
        User selectedUser = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND, "등록되지 않은 ID입니다."));

        // 비밀번호 틀림
        if(!encoder.matches(password, selectedUser.getPassword())){
            throw new AppException(ErrorCode.INVALID_PASSWORD, "비밀번호가 틀렸습니다.");
        }

        // 로그인 성공
        String token = JwtTokenUtil.createToken(userName, key, expireTimeMs);

        return token;
    }
}
