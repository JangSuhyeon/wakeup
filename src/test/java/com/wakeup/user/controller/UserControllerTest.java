package com.wakeup.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wakeup.exception.AppException;
import com.wakeup.exception.ErrorCode;
import com.wakeup.user.domain.UserLoginRequest;
import com.wakeup.user.domain.dto.UserJoinRequest;
import com.wakeup.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 성공")
    @WithMockUser
    void join() throws Exception {
        String userName = "jang";
        String password = "1234";

        mockMvc.perform(post("/user/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("회원가입 실패 - userName 중복")
    @WithMockUser
    void join_fail() throws Exception {
        String userName = "jang";
        String password = "1234";

        when(userService.join(any(),any(),any(),any()))
                        .thenThrow(new RuntimeException("해당 userId가 중복됩니다."));

        mockMvc.perform(post("/user/join")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("로그인 성공")
    @WithMockUser
    void login_success () throws Exception {
        String userName = "jang";
        String password = "1234";

        when(userService.login(any(),any()))
                        .thenReturn("token");

        mockMvc.perform(post("/user/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName, password))))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("로그인 실패 - 아이디 없음")
    @WithMockUser
    void login_fail1 () throws Exception {
        String userName = "jang";
        String password = "1234";

        when(userService.login(any(),any()))
                .thenThrow(new AppException(ErrorCode.USERNAME_NOT_FOUND, "해당 아이디가 없습니다."));

        mockMvc.perform(post("/user/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 틀림")
    @WithMockUser
    void login_fail2 () throws Exception {
        String userName = "jang";
        String password = "1234";

        when(userService.login(any(),any()))
                .thenThrow(new AppException(ErrorCode.INVALID_PASSWORD, "비밀번호가 틀렸습니다."));

        mockMvc.perform(post("/user/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(new UserLoginRequest(userName,password))))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}