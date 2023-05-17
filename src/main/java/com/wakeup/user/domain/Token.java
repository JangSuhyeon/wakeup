package com.wakeup.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;

@Builder
@Entity
public class Token {

    @Id
    String userName;

    @Column
    String refreshToken;

    @Column
    Long expireTime;

}
