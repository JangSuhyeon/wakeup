package com.wakeup.user.repository;

import com.wakeup.user.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {
    int countAllByUserName(String userName);
}
