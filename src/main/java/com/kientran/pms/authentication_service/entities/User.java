package com.kientran.pms.authentication_service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    public User(Long id, String username, String passwordHash, String token) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.token = token;
        this.id = id;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String passwordHash;
    private String token;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
