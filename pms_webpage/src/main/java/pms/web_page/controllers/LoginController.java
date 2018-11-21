package pms.web_page.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pms.web_page.security.SessionManager;
import pms.web_page.security.UserResponse;
import pms.web_page.security.UserSessionData;
import pms.web_page.services.Authentication.AuthenticationServiceInterface;
import pms.web_page.services.OTPService.OTPServiceInterface;
import pms.web_page.services.OTPService.VerifyingOTPResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    SessionManager sessionManager;

    @Autowired
    AuthenticationServiceInterface authenticationServiceInterface;

    @Autowired
    OTPServiceInterface otpServiceInterface;


    @RequestMapping("/")
    public void home(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = "token", required = false) String token) {

        if (token != null) {
            sessionManager.setSessionWithToken(token);
        }

        if (sessionManager.isLogged()) {
            navigateToPasswordManagement(response);
        } else {
            navigateToLoginPage(response);
        }
    }

    private void navigateToLoginPage(HttpServletResponse response) {
        try {
            response.sendRedirect("login");
        } catch (IOException e) {

        }

    }

    private void navigateToPasswordManagement(HttpServletResponse response) {
        try {
            response.sendRedirect("/management");
        } catch (IOException e) {

        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLoginPage(HttpServletRequest request, HttpServletResponse response) {

        Boolean isLogged = sessionManager.isLogged();
        if (isLogged) {
            navigateToPasswordManagement(response);
            return null;
        } else {
            return "login";
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request,
                      HttpServletResponse response,
                      @RequestParam(name="username") String username,
                      @RequestParam(name="passwordHash") String passwordHash
    ) {
        UserResponse userResponse = null;
        try {
            userResponse = authenticationServiceInterface.authenticate(username, passwordHash).getBody();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (userResponse == null) {
            navigateToLoginPage(response);
            return;
        }

        sessionManager.setSessionWithToken(userResponse);
        try {
            response.sendRedirect("otp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/otp", method = RequestMethod.GET)
    public String showOTP(HttpServletRequest request, HttpServletResponse response, Model model) {
        UserSessionData currentUser = sessionManager.currentUser();

        if (currentUser == null) {
            navigateToLoginPage(response);
            return "";
        }

        String otp = otpServiceInterface.createOTP(currentUser.getToken()).getBody().getOtp();
model.addAttribute("otp_value", otp);
        return "otp";
    }


    @RequestMapping(path = "/otp", method = RequestMethod.POST)
    public void verifyOTP(HttpServletRequest request,
                          HttpServletResponse response,
                          @RequestParam(name="otp") String otp
    ) {
        UserSessionData currentUser = sessionManager.currentUser();

        if (currentUser == null) {
            navigateToLoginPage(response);

        }


        VerifyingOTPResponse verifyingOTPResponse = otpServiceInterface.verifyOTP(currentUser.getToken(), otp).getBody();

        if (verifyingOTPResponse.getVerify() == true) {
    navigateToPasswordManagement(response);
        } else {
            try {
                response.sendRedirect("otp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
