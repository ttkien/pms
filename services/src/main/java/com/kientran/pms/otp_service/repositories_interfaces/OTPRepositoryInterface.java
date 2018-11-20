package com.kientran.pms.otp_service.repositories_interfaces;

public interface OTPRepositoryInterface {
    String createOTP(String token);
    Boolean verifyOTP(String token, String otp);

}
