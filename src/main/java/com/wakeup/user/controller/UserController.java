package com.wakeup.user.controller;

import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import com.wakeup.user.domain.User;
import com.wakeup.user.domain.UserLoginRequest;
import com.wakeup.user.domain.dto.UserJoinRequest;
import com.wakeup.user.domain.dto.UserJoinResponse;
import com.wakeup.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String goToLogin() {
        return "/user/login";
    }

    @ResponseBody
    @PostMapping("/loginFinish")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto, HttpServletResponse response) throws UnsupportedEncodingException {
        String token = userService.login(dto.getUserName(), dto.getPassword());
        token = "Bearer " + token;
        token = UriUtils.encode(token, StandardCharsets.UTF_8);
        Cookie cookie = new Cookie("token", token);
        cookie.setMaxAge(60 * 60 * 24); // 쿠키의 유효기간을 설정합니다. 이 예시에서는 1일로 설정합니다.
        cookie.setPath("/"); // 쿠키가 사용될 경로를 설정합니다. 이 예시에서는 모든 경로에서 사용됩니다.
        response.addCookie(cookie);

        log.info("userName : {} -> 로그인",dto.getUserName());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/join")
    public String goToJoin(){
        return "/user/join";
    }

    @ResponseBody
    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinRequest dto, Model model){
        UserJoinResponse user = userService.join(dto.getUserName(),dto.getPassword(),dto.getName(),dto.getEmail());
        model.addAttribute("user",user);

        log.info("userName : {} -> 회원가입",user.getUserName());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/join/complete")
    public String goToJoinComplete() {
        return "/user/joinComplete";
    }

}
