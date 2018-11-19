package pms.web_page.security;

public class UserResponse {

   public UserResponse(Long id, String username, String token) {
       this.username = username;
       this.token = token;
       this.id = id;
   }

    public UserResponse() {
    }

   private Long id;
   private String username;
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

   public String getToken() {
       return token;
   }

   public void setToken(String token) {
       this.token = token;
   }

}