package pms.web_page.services.PasswordManager;

public class PasswordResponse {
    private Long id;

    private String username;
    private String domain;
    private String domainUsername;

    private String encryptedPassword;
    private String clearPasswordHash;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDomain() {
        return domain;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getClearPasswordHash() {
        return clearPasswordHash;
    }

    public String getDomainUsername() {
        return domainUsername;
    }
}
