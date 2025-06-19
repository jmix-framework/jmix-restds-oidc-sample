package com.company.clientapp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.test.context.bean.override.convention.TestBean;

@SpringBootTest
public class ContextLoadedTest {

    @TestBean
    ClientRegistrationRepository clientRegistrationRepository;

    static ClientRegistrationRepository clientRegistrationRepository() {
        return Mockito.mock(ClientRegistrationRepository.class);
    }

    @Test
    void testContextLoaded() {

    }
}
