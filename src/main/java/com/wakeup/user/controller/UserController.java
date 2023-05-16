package com.wakeup.user.controller;

import com.wakeup.user.domain.dto.UserLoginRequest;
import com.wakeup.user.domain.dto.UserJoinRequest;
import com.wakeup.user.domain.dto.UserJoinResponse;
import com.wakeup.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
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
        cookie.setMaxAge(60 * 60 * 24); // 쿠키 유효기간 1일 (토큰 유효기간과 맞춰야 함)
        cookie.setPath("/"); // 쿠키가 사용될 경로
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
