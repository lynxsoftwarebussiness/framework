package com.itl.datasponsor.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value("${application.client.id}")
    private String clientId;

    @Value("${application.client.secret}")
    private String clientSecret;

    @Value("${application.access.token.validity.seconds}")
    private int accessTokenDuration;

    @Value("${application.refresh.token.validity.seconds}")
    private int refreshTokenDuration;

    @Value("${application.authorized.grant.types}")
    private String[] grantTypes;

    @Value("${application.authorities}")
    private String[] authorities;

    @Value("${application.scopes}")
    private String[] scopes;

    @Value("${application.resource.ids}")
    private String[] resourceIds;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId)
                .authorizedGrantTypes(grantTypes)
                .authorities(authorities)
                .scopes(scopes)
                .resourceIds(resourceIds)
                .accessTokenValiditySeconds(accessTokenDuration)
                .secret(passwordEncoder.encode(clientSecret))
                .refreshTokenValiditySeconds(refreshTokenDuration);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }
}
