package pms.web_page.controllers;

public class PasswordModel {
    public String id;

    public String username;
    public String domain;
    public String domainUsername;

    public String encryptedPassword;
    public String clearPasswordHash;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainUsername() {
        return domainUsername;
    }

    public void setDomainUsername(String domainUsername) {
        this.domainUsername = domainUsername;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getClearPasswordHash() {
        return clearPasswordHash;
    }

    public void setClearPasswordHash(String clearPasswordHash) {
        this.clearPasswordHash = clearPasswordHash;
    }
}
