package com.company.clientapp.security;

import io.jmix.oidc.OidcVaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class AppSecurityConfiguration extends OidcVaadinWebSecurity {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http.oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/oauth2/authorization/keycloak")
                .defaultSuccessUrl("/", true));
    }
}