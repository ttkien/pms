package com.kientran.pms.otp_service.repositories_impl;

import com.kientran.pms.otp_service.repositories_interfaces.OTPRepositoryInterface;

public class OTPRepository implements OTPRepositoryInterface {
    private static final String DEFAULT_OTP  = "111111";
    @Override
    public String createOTP(String token) {
        return DEFAULT_OTP;
    }

    @Override
    public Boolean verifyOTP(String token, String otp) {
        if (otp == null) {
            return false;
        }

        return DEFAULT_OTP.equals(otp);
    }
}
