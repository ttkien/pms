package com.kientran.pms.otp_service.response_models;

public class VerifyingOTPResponse {
    private Boolean isVerify;

    public Boolean getVerify() {
        return isVerify;
    }

    public void setVerify(Boolean verify) {
        isVerify = verify;
    }

    public VerifyingOTPResponse(Boolean isVerify) {
        this.isVerify = isVerify;
    }
}
