package com.kientran.pms.otp_service;


import com.kientran.pms.otp_service.repositories_interfaces.OTPRepositoryInterface;
import com.kientran.pms.otp_service.response_models.CreatingOTPResponse;
import com.kientran.pms.otp_service.response_models.VerifyingOTPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class OTPController {

    @Autowired
    OTPRepositoryInterface otpRepositoryInterface;

    @RequestMapping(value = "/otp", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<CreatingOTPResponse> createOTP(@RequestParam String token) {
        String newOTP = this.otpRepositoryInterface.createOTP(token);
        CreatingOTPResponse response = new CreatingOTPResponse(newOTP, token);
        return new ResponseEntity<CreatingOTPResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/otp/verify", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<VerifyingOTPResponse> verifyOTP(@RequestParam(name="token", required = false) String token,
                                                          @RequestParam (name="otp", required = false) String otp) {
        Boolean isVerified = this.otpRepositoryInterface.verifyOTP(token, otp);
        VerifyingOTPResponse response = new VerifyingOTPResponse(isVerified);
        return new ResponseEntity<VerifyingOTPResponse>(response, HttpStatus.OK);

    }
}

