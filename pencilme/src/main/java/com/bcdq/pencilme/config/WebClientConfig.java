package com.bcdq.pencilme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

/**
 * 서버 간 통신을 위해 사용하는 WebClient 설정 요소를 담은 클래스
 *
 * @author Juwon Lee
 */
@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webclientBuilder() {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, "*/*")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .clientConnector(new ReactorClientHttpConnector(HttpClient.newConnection()
                        .responseTimeout(Duration.ofSeconds(60))
                ));
    }
}
