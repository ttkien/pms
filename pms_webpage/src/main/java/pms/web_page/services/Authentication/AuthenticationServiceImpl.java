package pms.web_page.services.Authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pms.web_page.Utils;
import pms.web_page.security.UserResponse;

import java.net.URI;
import java.util.HashMap;

public class AuthenticationServiceImpl implements AuthenticationServiceInterface {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<UserResponse> authenticate(String username, String passwordHash) {


        String currentHost = Utils.getWebServiceDomain();
        URI uriComponents = UriComponentsBuilder.fromHttpUrl(currentHost + "/api/authenticate")
                .build().toUri();

        HashMap<String, String> parametersMap = new HashMap<String, String>();
        parametersMap.put("username", username);
        parametersMap.put("passwordHash", passwordHash);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(parametersMap);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(jsonString, headers);

            return restTemplate.exchange(uriComponents, HttpMethod.POST, entity, UserResponse.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<UserResponse> authenticate(String token) {

        String currentHost = Utils.getWebServiceDomain();
        URI uriComponents = UriComponentsBuilder.fromHttpUrl(currentHost + "/api/authenticateByToken")
                .queryParam("token", token)
                .build().toUri();

        return restTemplate.getForEntity(uriComponents, UserResponse.class);
    }
}
