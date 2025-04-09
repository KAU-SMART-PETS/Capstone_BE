package com.kau.capstone.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi getItemApi() {
        return GroupedOpenApi
            .builder()
            .group("v1")
            .pathsToMatch("/api/v1/**")
            .build();
    }

    @Bean
    public GroupedOpenApi getMemberApi() {
        return GroupedOpenApi
            .builder()
            .group("v2")
            .pathsToMatch("/api/v2/**")
            .build();
    }

    @Bean
    public OpenAPI getOpenApi() {
        Info info = new Info()
            .title("CAPSTONE DESIGN")
            .description("2024 캡스톤 디자인 앱 개발 Swagger 입니다");

        return new OpenAPI()
            .components(new Components())
            .info(info);
    }
}
