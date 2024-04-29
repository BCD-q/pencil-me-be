package com.bcdq.pencilme.config;

import com.bcdq.pencilme.config.security.CustomAuthenticationEntryPoint;
import com.bcdq.pencilme.config.security.JwtAuthenticationFilter;
import com.bcdq.pencilme.config.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 필터 설정 요소를 담은 클래스
 *
 * @author Juwon Lee
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] PERMIT_URI_ARRAY = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/members/login",
            "/api/v1/members"
    };

    private final TokenProvider tokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers(PERMIT_URI_ARRAY).permitAll()
                .antMatchers(HttpMethod.GET, "*").permitAll()
                .antMatchers("**exception**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .cors();
        return httpSecurity.build();
    }


}
