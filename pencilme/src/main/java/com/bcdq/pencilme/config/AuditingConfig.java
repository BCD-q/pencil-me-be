package com.bcdq.pencilme.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Auditing에 대한 설정 요소를 담은 클래스
 *
 * @author Juwon Lee
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfig {
}
