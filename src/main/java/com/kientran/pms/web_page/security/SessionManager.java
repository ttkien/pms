package com.kientran.pms.web_page.security;

public interface SessionManager {

    void setSessionWithToken(String token);
    UserSessionData getUserWithToken(String token);
    Boolean isLogged();
    UserSessionData currentUser();
}
