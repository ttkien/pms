package pms.web_page;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriBuilderFactory;
import pms.web_page.security.SessionManager;
import pms.web_page.security.SimpleSessionManager;
import pms.web_page.services.AuthenticationServiceImpl;
import pms.web_page.services.AuthenticationServiceInterface;

import java.net.URI;
import java.util.Map;

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
}
