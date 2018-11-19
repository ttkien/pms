package com.kientran.pms.authentication_service;

import com.kientran.pms.authentication_service.repository_impl.MockUserRepository;
import com.kientran.pms.authentication_service.repository_interfaces.UserRepositoryInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationConfiguration {
    @Bean
    public UserRepositoryInterface getUserRepositoryInterface() {
        return new MockUserRepository();
    }
}
