package com.company.serviceapp.security;

import io.jmix.oidc.OidcVaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class AppSecurityConfiguration extends OidcVaadinWebSecurity {

    protected void configureJmixSpecifics(HttpSecurity http) throws Exception {
        super.configureJmixSpecifics(http);

        http.oauth2Login(oauth2Login -> {
            oauth2Login.loginPage("/oauth2/authorization/keycloak").defaultSuccessUrl("/", true);
        });
    }

}