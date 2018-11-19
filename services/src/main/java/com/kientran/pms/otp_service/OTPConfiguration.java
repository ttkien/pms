package com.kientran.pms.otp_service;

import com.kientran.pms.otp_service.repositories_impl.OTPRepository;
import com.kientran.pms.otp_service.repositories_interfaces.OTPRepositoryInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OTPConfiguration {
    @Bean
    public OTPRepositoryInterface getOTPRepositoryInterface() {
        return new OTPRepository();
    }

}
