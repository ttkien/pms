package com.kientran.pms.otp_service.response_models;

public class CreatingOTPResponse {
    public CreatingOTPResponse(String otp, String token) {
        this.otp = otp;
        this.token = token;
    }

    private String otp;
    private String token;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
