package pms.web_page;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pms.web_page.security.SessionManager;
import pms.web_page.security.SimpleSessionManager;
import pms.web_page.services.Authentication.AuthenticationServiceImpl;
import pms.web_page.services.Authentication.AuthenticationServiceInterface;
import pms.web_page.services.OTPService.OTPServiceImpl;
import pms.web_page.services.OTPService.OTPServiceInterface;

@Configuration
public class WebPageConfiguration {
    @Bean
    public SessionManager getSessionManager() {
        return new SimpleSessionManager();
    }

    @Bean
    public RestTemplate getRestTemplate() {

        RestTemplate restTemplate = new RestTemplateBuilder().build();

        return restTemplate;
    }

    @Bean
    public AuthenticationServiceInterface getAuthenticationServiceInterface() {
        return new AuthenticationServiceImpl();
    }


    @Bean
    public OTPServiceInterface getOTOtpServiceInterface() {
        return new OTPServiceImpl();
    }
}
