package pms.web_page.services.OTPService;


import org.springframework.http.ResponseEntity;

public interface OTPServiceInterface {
    ResponseEntity<CreatingOTPResponse> createOTP(String token);
    ResponseEntity<VerifyingOTPResponse> verifyOTP(String token, String otp);
}
