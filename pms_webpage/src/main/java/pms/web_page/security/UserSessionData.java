package pms.web_page.security;

public class UserSessionData {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String username;
    private String token;

    public Boolean getVerifiedOTP() {
        return isVerifiedOTP;
    }

    public void setVerifiedOTP(Boolean verifiedOTP) {
        isVerifiedOTP = verifiedOTP;
    }

    private Boolean isVerifiedOTP = false;
    public UserSessionData(Long id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }

}
