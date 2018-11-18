package com.kientran.pms.web_page;

import com.kientran.pms.web_page.security.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String login(HttpServletRequest request, HttpServletResponse response) {

        Boolean isLogged = sessionManager.isLogged();
        if (isLogged) {
            navigateToPasswordManagement(response);
            return "";
        } else {
            return "Login";
        }
    }
}
