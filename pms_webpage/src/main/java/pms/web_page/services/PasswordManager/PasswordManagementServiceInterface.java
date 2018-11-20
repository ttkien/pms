package pms.web_page.services.PasswordManager;


import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PasswordManagementServiceInterface {

    ResponseEntity<List<PasswordResponse>> getAll(String username, String domain);

    ResponseEntity<PasswordResponse> createPassword(CreatePasswordRequestParam request) throws InvalidArgumentException;

    ResponseEntity<PasswordResponse> updatePassword(Long id, UpdatePasswordRequestParam request) throws InvalidArgumentException;

    ResponseEntity<String> deletePassword(Long id);
}
