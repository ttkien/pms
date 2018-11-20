package pms.web_page.controllers;


import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pms.web_page.security.SessionManager;
import pms.web_page.security.UserResponse;
import pms.web_page.security.UserSessionData;
import pms.web_page.services.Authentication.AuthenticationServiceInterface;
import pms.web_page.services.OTPService.OTPServiceInterface;
import pms.web_page.services.OTPService.VerifyingOTPResponse;
import pms.web_page.services.PasswordManager.PasswordManagementServiceInterface;
import pms.web_page.services.PasswordManager.PasswordResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class PasswordManagementController {
    @Autowired
    SessionManager sessionManager;

    @Autowired
    PasswordManagementServiceInterface passwordManagementServiceInterface;

    @RequestMapping("/management")
    public String home(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        UserSessionData currentUser = sessionManager.currentUser();

        if (currentUser == null) {
            navigateToLoginPage(response);
        }

         ResponseEntity<List<PasswordResponse>> allPasswordsFromCurrentUser
                = passwordManagementServiceInterface.getAll(currentUser.getUsername(), null);

        List<PasswordModel> passwordModels = allPasswordsFromCurrentUser.getBody().stream().map(passwordResponse -> {
            PasswordModel passwordModel = new PasswordModel();
            passwordModel.id = "" + passwordResponse.getId();
            passwordModel.username = passwordResponse.getUsername();
            passwordModel.domain = passwordResponse.getDomain();
            passwordModel.domainUsername = passwordResponse.getDomainUsername();
            passwordModel.encryptedPassword = passwordResponse.getEncryptedPassword();
            passwordModel.clearPasswordHash = passwordResponse.getClearPasswordHash();

            return passwordModel;
        }).collect(Collectors.toList());


        model.addAttribute("passwords", passwordModels);
        return "passwordManagement";
    }

    private void navigateToLoginPage(HttpServletResponse response) {
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {

        }

    }
}

class PasswordModel {
    public String id;

    public String username;
    public String domain;
    public String domainUsername;

    public String encryptedPassword;
    public String clearPasswordHash;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainUsername() {
        return domainUsername;
    }

    public void setDomainUsername(String domainUsername) {
        this.domainUsername = domainUsername;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getClearPasswordHash() {
        return clearPasswordHash;
    }

    public void setClearPasswordHash(String clearPasswordHash) {
        this.clearPasswordHash = clearPasswordHash;
    }
}