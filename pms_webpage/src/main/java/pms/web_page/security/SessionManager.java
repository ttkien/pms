package pms.web_page.security;

public interface SessionManager {

    void setSessionWithToken(String token);
    void setSessionWithToken(UserResponse userResponse);
    UserSessionData getUserWithToken(String token);
    Boolean isLogged();
    UserSessionData currentUser();

    void clearSession();
    void setVerifedOTP(boolean isVerified);
}
