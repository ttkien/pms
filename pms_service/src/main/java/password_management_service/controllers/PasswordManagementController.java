package password_management_service.controllers;

import password_management_service.entities.Password;
import password_management_service.services.PasswordService;
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
    public ResponseEntity<List<Password>> getAll(@RequestParam String username, @RequestParam(required = false) String domain) {

        return new ResponseEntity<>(passwordService.getAll(username, domain), HttpStatus.OK);
    }

    @RequestMapping(value = "/passwords", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Password> createPassword(CreatePasswordRequestParam request) throws NullPointerException {

        if (request.getUsername() == null
                || request.getDomain() == null
                || request.getEncryptedPassword() == null
                || request.getClearPasswordHash() == null
        ) {
            throw new NullPointerException();
        }

        Password password = new Password(
                request.getUsername(),
                request.getDomain(),
                request.getEncryptedPassword(),
                request.getClearPasswordHash()
        );

        passwordService.saveOrUpdate(password);

        return new ResponseEntity<>(password, HttpStatus.OK);

    }

    @RequestMapping(value = "/passwords/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Password> updatePassword(@PathVariable Long id, UpdatePasswordRequestParam request) throws NullPointerException {

        if (id == null
                || request.getDomain() == null
                || request.getEncryptedPassword() == null
                || request.getClearPasswordHash() == null
        ) {
            throw new NullPointerException();
        }

        Password storedPassword = passwordService.getPersonById(id);
        storedPassword.setDomain(request.getDomain());
        storedPassword.setEncryptedPassword(request.getEncryptedPassword());
        storedPassword.setClearPasswordHash(request.getClearPasswordHash());

        passwordService.saveOrUpdate(storedPassword);

        return new ResponseEntity<>(storedPassword, HttpStatus.OK);

    }

    @RequestMapping(value = "/passwords/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deletePassword(@PathVariable Long id) {
        passwordService.delete(id);

        return new ResponseEntity<>("", HttpStatus.OK);
    }
}

