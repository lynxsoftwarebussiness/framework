package com.itl.datasponsor.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigurerAdapter implements WebMvcConfigurer {

    @Value("${application.allowed.methods}")
    private String[] allowMethods;

    @Value("${application.allowed.origins}")
    private String[] allowOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods(allowMethods)
                .allowedOrigins(allowOrigins);
    }


}
