package com.codestates.pre_028.stackoverflow_clone.Auth.handler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
@Slf4j
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        String refreshToken = response.getHeader("Refresh");
        Cookie cookie = new Cookie("jwt_token", refreshToken);
        cookie.setPath("/");
        cookie.setHttpOnly(true);


        response.addCookie(cookie);// 쿠키 세팅

        log.info(" # 인증 성공입니다!");
    }
}
