package pms.web_page.services.OTPService;

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

    public VerifyingOTPResponse() {
    }
}
