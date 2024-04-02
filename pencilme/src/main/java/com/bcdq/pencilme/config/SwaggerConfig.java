package com.bcdq.pencilme.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger-ui 설정 요소를 담은 클래스
 *
 * @author Juwon Lee
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        SecurityScheme apikey = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityItem = new SecurityRequirement().addList("basicAuth");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("basicAuth", apikey))
                .addSecurityItem(securityItem)
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Pencil me API")
                .description("개발자를 위한 생산성 도구 - Pencil me의 API 명세입니다")
                .version("1.0.0");
    }
}
