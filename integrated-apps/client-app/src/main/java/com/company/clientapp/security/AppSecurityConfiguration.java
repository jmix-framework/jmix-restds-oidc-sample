package com.company.clientapp.security;

import com.vaadin.flow.spring.security.VaadinSecurityConfigurer;
import io.jmix.oidc.OidcVaadinWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class AppSecurityConfiguration extends OidcVaadinWebSecurity {

    @Override
    protected void configureVaadinSpecifics(HttpSecurity http) {
        http.with(VaadinSecurityConfigurer.vaadin(), configurer -> configurer
                .oauth2LoginPage("/oauth2/authorization/keycloak")
                .defaultSuccessUrl("/", true));
    }
}
