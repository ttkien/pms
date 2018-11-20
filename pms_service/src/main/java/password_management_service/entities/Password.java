package password_management_service.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Password {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String domain;
    private String encryptedPassword;
    private String encryptedPasswordHash;

    public Password(String username, String domain, String encryptedPassword, String encryptedPasswordHash) {
        this.username = username;
        this.domain = domain;
        this.encryptedPassword = encryptedPassword;
        this.encryptedPasswordHash = encryptedPasswordHash;
    }

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

    public String getEncryptedPasswordHash() {
        return encryptedPasswordHash;
    }

    public void setEncryptedPasswordHash(String encryptedPasswordHash) {
        this.encryptedPasswordHash = encryptedPasswordHash;
    }

    protected Password() {}

}
