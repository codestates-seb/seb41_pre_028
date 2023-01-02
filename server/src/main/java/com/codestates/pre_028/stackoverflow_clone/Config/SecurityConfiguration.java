package com.codestates.pre_028.stackoverflow_clone.Config;

import com.codestates.pre_028.stackoverflow_clone.Auth.Jwt.JwtTokenizer;
import com.codestates.pre_028.stackoverflow_clone.Auth.filter.JwtAuthenticationFilter;
import com.codestates.pre_028.stackoverflow_clone.Auth.filter.JwtVerificationFilter;
import com.codestates.pre_028.stackoverflow_clone.Auth.handler.UserAccessDeniedHandler;
import com.codestates.pre_028.stackoverflow_clone.Auth.handler.UserAuthenticationEntryPoint;
import com.codestates.pre_028.stackoverflow_clone.Auth.handler.UserAuthenticationFailureHandler;
import com.codestates.pre_028.stackoverflow_clone.Auth.handler.UserAuthenticationSuccessHandler;
import com.codestates.pre_028.stackoverflow_clone.Auth.utils.CustomAuthorityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils customAuthorityUtils;

    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils customAuthorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.customAuthorityUtils = customAuthorityUtils;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // H2 DB 사용 가능하게 설정
        http
                .headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()   // form 로그인 안쓰고 프론트 사용
                .httpBasic().disable()   //
                .exceptionHandling()
                .authenticationEntryPoint(new UserAuthenticationEntryPoint())
                .accessDeniedHandler(new UserAccessDeniedHandler())
                .and()
                .apply(new CustomFilterConfigurer())
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/users/**").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasAnyRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/questions?page=0&size=15")
                .deleteCookies("jwt_token")

        ;// 역할에 다른 기능 추가

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://41-pre-project-028.s3-website.ap-northeast-2.amazonaws.com", "http://localhost:3000")); // 프론트엔드 쪽 서버 주소
        configuration.setAllowedHeaders(Arrays.asList("*"));  //쿠키 커스텀한 헤더들?
        configuration.setExposedHeaders(Arrays.asList("Authorization", "jwt_token","Set-Cookie"));  //쿠키 미확인 해결
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PATCH", "DELETE"));
        configuration.setAllowCredentials(true);  //
        configuration.setMaxAge(3000L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {

            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/login");  // -> /login
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new UserAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, customAuthorityUtils);

            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}
