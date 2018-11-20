package com.kientran.pms.authentication_service;

import com.kientran.pms.authentication_service.entities.User;
import com.kientran.pms.authentication_service.repository_interfaces.UserRepositoryInterface;
import com.kientran.pms.authentication_service.request_models.AuthencationCredentials;
import com.kientran.pms.authentication_service.response_models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class AuthenticationController {

    @Autowired
    UserRepositoryInterface userRepositoryInterface;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserResponse> authenticate(@RequestBody AuthencationCredentials credentials) {

        User user = userRepositoryInterface.getUser(credentials.getUsername(), credentials.getPasswordHash());

        if (user == null) {
            return new ResponseEntity<UserResponse>(HttpStatus.BAD_REQUEST);
        } else {
            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getToken()
            );

            return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/authenticateByToken", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> authenticate(String token) {
        User user = userRepositoryInterface.getUser(token);

        if (user == null) {
            return new ResponseEntity<UserResponse>(HttpStatus.BAD_REQUEST);
        } else {
            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getToken()
            );

            return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
        }
    }
}

