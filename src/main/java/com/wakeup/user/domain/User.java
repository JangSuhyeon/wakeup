package com.wakeup.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column
    private String password;

    @Column
    private String email;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 회원정보 수정
    public void modify(String password, String name) {
        this.password = password;
        this.name = name;
    }

    // 소셜로그인시 수정날짜 업데이트 (필요?)
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }

}
