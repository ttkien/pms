package com.kientran.pms.authentication_service.request_models;

public class AuthencationCredentials {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    private String username;
    private String passwordHash;

}
