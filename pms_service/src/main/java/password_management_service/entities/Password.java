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
    private String clearPasswordHash;

    public Password(String username, String domain, String encryptedPassword, String clearPasswordHash) {
        this.username = username;
        this.domain = domain;
        this.encryptedPassword = encryptedPassword;
        this.clearPasswordHash = clearPasswordHash;
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

    public String getClearPasswordHash() {
        return clearPasswordHash;
    }

    public void setClearPasswordHash(String clearPasswordHash) {
        this.clearPasswordHash = clearPasswordHash;
    }

    public Password() {}

}
