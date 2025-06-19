package com.company.serviceapp.test_support;

import org.mockito.Mockito;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.bean.override.convention.TestBean;

public class BaseIntegrationTest {

    @TestBean
    ClientRegistrationRepository clientRegistrationRepository;

    static ClientRegistrationRepository clientRegistrationRepository() {
        return Mockito.mock(ClientRegistrationRepository.class);
    }
}
