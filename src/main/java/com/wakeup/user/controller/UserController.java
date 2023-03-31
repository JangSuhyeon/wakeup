package com.wakeup.user.controller;

import com.wakeup.user.domain.User;
import com.wakeup.user.domain.UserLoginRequest;
import com.wakeup.user.domain.dto.UserJoinRequest;
import com.wakeup.user.domain.dto.UserJoinResponse;
import com.wakeup.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto){
        String token = userService.login(dto.getUserName(), dto.getPassword());
        return ResponseEntity.ok().body(token);
    }

    @GetMapping("/join")
    public String goToJoin(){
        return "/user/join";
    }

    @PostMapping("/join")
    public String join(@RequestBody UserJoinRequest dto, Model model){
        UserJoinResponse user = userService.join(dto.getUserName(),dto.getPassword(),dto.getName(),dto.getEmail());
        model.addAttribute("user",user);
        return "/user/joinComplete";
    }

}
