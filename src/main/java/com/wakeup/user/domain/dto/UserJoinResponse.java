package com.wakeup.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter
@Builder
public class UserJoinResponse {
    private String userName;
    private String name;
    private String email;
}
