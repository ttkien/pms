package com.kientran.pms.password_management_service;

import com.kientran.pms.otp_service.repositories_interfaces.OTPRepositoryInterface;
import com.kientran.pms.otp_service.response_models.CreatingOTPResponse;
import com.kientran.pms.otp_service.response_models.VerifyingOTPResponse;
import com.kientran.pms.password_management_service.entities.Password;
import com.kientran.pms.password_management_service.repositories.PasswordRepository;
import com.kientran.pms.password_management_service.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class PasswordManagementController {

@Autowired
    PasswordService passwordService;


    @RequestMapping(value = "/passwords", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Password>> getAll(@RequestParam String username) {

        return new ResponseEntity<>(passwordService.getAll(username, null), HttpStatus.OK);
    }
}

