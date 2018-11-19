package pms.web_page.services.Authentication;


import org.springframework.http.ResponseEntity;
import pms.web_page.security.UserResponse;

public interface AuthenticationServiceInterface {
    ResponseEntity<UserResponse> authenticate(String username, String passwordHash);
    ResponseEntity<UserResponse> authenticate(String token);
}
