package com.kientran.pms.web_page;


import com.kientran.pms.web_page.security.SessionManager;
import com.kientran.pms.web_page.security.SimpleSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebPageConfiguration {
    @Bean
    public SessionManager getSessionManager() {
        return new SimpleSessionManager();
    }

}
