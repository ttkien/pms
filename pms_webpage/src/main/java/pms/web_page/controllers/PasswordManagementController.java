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
import pms.web_page.services.PasswordManager.CreatePasswordRequestParam;
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

    @RequestMapping(value = "/management/add", method = RequestMethod.GET)
    public String showAddPassword(
            HttpServletRequest request,
            HttpServletResponse response) {
        UserSessionData currentUser = sessionManager.currentUser();

        if (currentUser == null) {
            navigateToLoginPage(response);
        }

        return "AddPassword";
    }

    @RequestMapping(value = "/management/add", method = RequestMethod.POST)
    public void addPassword(
            HttpServletRequest request,
            HttpServletResponse response,
            CreatePasswordRequestParam createPasswordRequestParam) throws NullPointerException, IOException {
        UserSessionData currentUser = sessionManager.currentUser();

        if (currentUser == null) {
            navigateToLoginPage(response);
        }
        createPasswordRequestParam.setUsername(currentUser.getUsername());
        ResponseEntity<PasswordResponse> password = passwordManagementServiceInterface.createPassword(createPasswordRequestParam);
        response.sendRedirect("/");

    }

    private void navigateToLoginPage(HttpServletResponse response) {
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {

        }

    }
}

