package com.kau.capstone.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
            .version("v1.0.0")
            .title("CAPSTONE DESIGN")
            .description("2024 캡스톤 디자인 앱 개발 Swagger 입니다");

        return new OpenAPI()
            .info(info);
    }
}
