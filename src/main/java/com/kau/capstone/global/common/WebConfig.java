package com.kau.capstone.global.common;

import com.kau.capstone.domain.auth.util.AuthArgumentResolver;
import com.kau.capstone.domain.auth.util.AuthHandlerInterceptor;
import com.kau.capstone.domain.pet.util.PetHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthArgumentResolver authArgumentResolver;
    private final AuthHandlerInterceptor authHandlerInterceptor;
    private final PetHandlerInterceptor petHandlerInterceptor;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor)
                .addPathPatterns("/api/v1/users", "/api/v1/users/**")
                .addPathPatterns("/api/v1/logout")
                .addPathPatterns("/api/v1/points", "/api/v1/points/**")
                .addPathPatterns("/api/v1/foods/**").excludePathPatterns("/api/v1/foods")
                .addPathPatterns("/api/v1/bluetooth")
                .addPathPatterns("/api/v1/rewards", "/api/v1/rewards/**")
                .addPathPatterns("/api/v1/alarm")
                .addPathPatterns("/api/v1/logout")
                .addPathPatterns("/api/v1/oauth2/logout");
        registry.addInterceptor(petHandlerInterceptor)
                .addPathPatterns("/api/v1/pets", "/api/v1/pets/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
