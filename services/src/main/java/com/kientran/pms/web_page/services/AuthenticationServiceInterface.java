package com.kientran.pms.web_page.services;


import com.kientran.pms.web_page.security.UserResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationServiceInterface {
    ResponseEntity<UserResponse> authenticate(String username, String passwordHash);
    ResponseEntity<UserResponse> authenticate(String token);
}
