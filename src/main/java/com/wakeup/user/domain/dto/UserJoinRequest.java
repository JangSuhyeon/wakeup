package com.wakeup.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String name;
    private String email;
}
