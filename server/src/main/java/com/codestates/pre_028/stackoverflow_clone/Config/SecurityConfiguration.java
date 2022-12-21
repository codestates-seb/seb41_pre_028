package com.codestates.pre_028.stackoverflow_clone.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .securityMatcher("/h2**")
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/h2/**","favicon.ico").permitAll()
                        .anyRequest()
                        .authenticated()
                );
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
}

