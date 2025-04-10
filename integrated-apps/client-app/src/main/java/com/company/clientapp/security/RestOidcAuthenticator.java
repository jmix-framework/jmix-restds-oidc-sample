package com.company.clientapp.security;

import io.jmix.restds.impl.RestAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class RestOidcAuthenticator implements RestAuthenticator {

    @Autowired
    private OAuth2AuthorizedClientManager authorizedClientManager;

    @Override
    public void setDataStoreName(String name) {
    }

    @Override
    public ClientHttpRequestInterceptor getAuthenticationInterceptor() {
        return new AuthenticatingClientHttpRequestInterceptor();
    }

    private String getAccessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("Cannot get access token: Authentication object is null");
        }

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest.withClientRegistrationId("keycloak")
                .principal(authentication)
                .build();

        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
        if (authorizedClient == null) {
            throw new IllegalStateException("Cannot authorize " + authorizeRequest);
        }
        return authorizedClient.getAccessToken().getTokenValue();
    }

    private class AuthenticatingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            request.getHeaders().setBearerAuth(getAccessToken());
            return execution.execute(request, body);
        }
    }
}
