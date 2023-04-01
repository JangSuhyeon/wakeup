package com.wakeup.user.controller;

import com.wakeup.user.domain.User;
import com.wakeup.user.domain.UserLoginRequest;
import com.wakeup.user.domain.dto.UserJoinRequest;
import com.wakeup.user.domain.dto.UserJoinResponse;
import com.wakeup.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest dto){
        System.out.println();
        System.out.println("dto : " + dto);
        String token = userService.login(dto.getUserName(), dto.getPassword());

        log.info("userName : {} -> 로그인",dto.getUserName());

        return ResponseEntity.ok().body(token);
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
