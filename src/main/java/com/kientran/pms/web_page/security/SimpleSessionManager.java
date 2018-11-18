package com.kientran.pms.web_page.security;

import com.kientran.pms.web_page.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


public class SimpleSessionManager implements SessionManager {

    @Override
    public void setSessionWithToken(String token) {
        UserSessionData userSessionData = getUserWithToken(token);
        if (userSessionData != null) {
            Utils.getSession().setAttribute("user", userSessionData);
        }
    }

    @Override
    public UserSessionData getUserWithToken(String token) {
        RestTemplate restTemplate = new RestTemplate();

        String currentHost = Utils.getHost();
        String authenticationUrl = currentHost;

        URI uriComponents = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/api/authenticateByToken")
                .queryParam("token", token)
                .build().toUri();


        ResponseEntity<UserResponse> responseEntity = restTemplate.getForEntity(uriComponents, UserResponse.class);

        UserResponse userResponse = responseEntity.getBody();
        if (userResponse == null) {
            return null;
        } else {
            UserSessionData userSessionData = new UserSessionData(userResponse.getId(),
                    userResponse.getUsername(), userResponse.getToken());
            return userSessionData;
        }
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
