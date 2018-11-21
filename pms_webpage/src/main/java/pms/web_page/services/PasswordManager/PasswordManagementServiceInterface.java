package pms.web_page.services.PasswordManager;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PasswordManagementServiceInterface {

    ResponseEntity<List<PasswordResponse>> getAll(String username, String domain);

    ResponseEntity<PasswordResponse> createPassword(CreatePasswordRequestParam request) throws NullPointerException;

    ResponseEntity<PasswordResponse> updatePassword(Long id, UpdatePasswordRequestParam request) throws NullPointerException;

    ResponseEntity<String> deletePassword(Long id);
}
