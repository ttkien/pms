package com.kientran.pms.web_page.security;

import com.kientran.pms.web_page.Utils;
import com.kientran.pms.web_page.services.AuthenticationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


public class SimpleSessionManager implements SessionManager {

    @Autowired
    AuthenticationServiceInterface authenticationServiceInterface;

    @Override
    public void setSessionWithToken(String token) {
        UserSessionData userSessionData = getUserWithToken(token);
        if (userSessionData != null) {
            Utils.getSession().setAttribute("user", userSessionData);
        }
    }

    @Override
    public void setSessionWithToken(UserResponse userResponse) {
        UserSessionData userSessionData = getUserSessionData(userResponse);
        if (userSessionData != null) {
            Utils.getSession().setAttribute("user", userSessionData);
        }
    }

    @Override
    public UserSessionData getUserWithToken(String token) {
        ResponseEntity<UserResponse> responseEntity = authenticationServiceInterface.authenticate(token);

        UserResponse userResponse = responseEntity.getBody();
        if (userResponse == null) {
            return null;
        } else {
            return getUserSessionData(userResponse);
        }
    }

    private UserSessionData getUserSessionData(UserResponse userResponse) {
        UserSessionData userSessionData = new UserSessionData(userResponse.getId(),
                userResponse.getUsername(), userResponse.getToken());
        return userSessionData;
    }

    @Override
    public Boolean isLogged() {
        return currentUser() != null;
    }

    @Override
    public UserSessionData currentUser() {
        return (UserSessionData) Utils.getSession().getAttribute("user");

    }
}
