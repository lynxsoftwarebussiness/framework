package com.itl.datasponsor.backend.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${application.client.id}")
    private String clientId;

    @Value("${application.client.secret}")
    private String clientSecret;

    @Value("${application.base.package}")
    private String basePackage;

    @Value("${application.name}")
    private String applicationName;

    @Value("${server.port}")
    private int serverPort;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any()).build()
                .securitySchemes(Collections.singletonList(oauth()))
                .securityContexts(Collections.singletonList(securityContext()))
                .pathMapping("/");
    }

    @Bean
    List<GrantType> grantTypes() {
        List<GrantType> grantTypes = new ArrayList<>();
        grantTypes.add(new ResourceOwnerPasswordCredentialsGrant(String.format("http://localhost:%d/oauth/token", serverPort)));
        return grantTypes;
    }

    @Bean
    SecurityScheme oauth() {
        return new OAuthBuilder().name("oauth2schema").scopes(scopes()).grantTypes(grantTypes()).build();
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return SecurityConfigurationBuilder.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .appName(applicationName)
                .scopeSeparator(" ")
                .build();
    }

    private List<AuthorizationScope> scopes() {
        return Arrays.asList(authorizationScopes());
    }

    private AuthorizationScope[] authorizationScopes() {
        return new AuthorizationScope[]{
                new AuthorizationScope("read", "Grants read access"),
                new AuthorizationScope("write", "Grants write access"),
                new AuthorizationScope("trust", "Grants read write and delete access")
        };
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes())))
                .forPaths(PathSelectors.ant("/api/**"))
                .build();
    }
}
