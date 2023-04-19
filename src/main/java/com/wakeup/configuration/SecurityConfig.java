package com.wakeup.configuration;

import com.wakeup.oauth2.CustomOAuth2UserService;
import com.wakeup.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;

    @Value("${jwt.token.secret}")
    private String secretKey;

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests()
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/user/join/**").permitAll()
                .requestMatchers("/user/loginFinish").permitAll()
                .requestMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .formLogin()
                .loginPage("/user/login")
                .permitAll();

        httpSecurity
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

        httpSecurity.logout()
                .deleteCookies("token");

        return httpSecurity.addFilterBefore(new JwtFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}
