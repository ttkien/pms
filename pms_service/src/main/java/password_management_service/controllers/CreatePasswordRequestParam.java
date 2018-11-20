package password_management_service.controllers;

class CreatePasswordRequestParam {
    private String username;
    private String domain;
    private String encryptedPassword;
    private String clearPasswordHash;

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
