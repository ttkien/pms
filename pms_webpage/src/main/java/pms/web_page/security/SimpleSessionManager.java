package pms.web_page.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import pms.web_page.Utils;
import pms.web_page.services.Authentication.AuthenticationServiceInterface;


public class SimpleSessionManager implements SessionManager {

    @Autowired
    AuthenticationServiceInterface authenticationServiceInterface;

    @Override
    public void setSessionWithToken(String token) {
        UserSessionData userSessionData = getUserWithToken(token);
        if (userSessionData != null) {
            saveSessionData(userSessionData);
        }
    }

    @Override
    public void setSessionWithToken(UserResponse userResponse) {
        UserSessionData userSessionData = getUserSessionData(userResponse);
        if (userSessionData != null) {
            saveSessionData(userSessionData);
        }
    }

    private void saveSessionData(UserSessionData userSessionData) {
        Utils.getSession().setAttribute("user", userSessionData);
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
        return currentUser() != null
                && currentUser().getVerifiedOTP();
    }

    @Override
    public UserSessionData currentUser() {
        return (UserSessionData) Utils.getSession().getAttribute("user");

    }

    @Override
    public void clearSession() {
        Utils.getSession().removeAttribute("user");
    }

    @Override
    public void setVerifedOTP(boolean isVerified) {
        UserSessionData user = currentUser();
        if (user == null) {
        }

        user.setVerifiedOTP(isVerified);
        saveSessionData(user);
    }
}
