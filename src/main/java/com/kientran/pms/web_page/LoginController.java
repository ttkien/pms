package com.kientran.pms.web_page;

import com.kientran.pms.authentication_service.request_models.AuthencationCredentials;
import com.kientran.pms.web_page.security.SessionManager;
import com.kientran.pms.web_page.security.UserResponse;
import com.kientran.pms.web_page.services.AuthenticationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    SessionManager sessionManager;

    @Autowired
    AuthenticationServiceInterface authenticationServiceInterface;

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
    public String showLoginPage(HttpServletRequest request, HttpServletResponse response, Model model) {

        Boolean isLogged = sessionManager.isLogged();
        if (isLogged) {
            navigateToPasswordManagement(response);
            return "";
        } else {
            model.addAttribute("test", "kienhipepsi");
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
        navigateToPasswordManagement(response);
    }
}
