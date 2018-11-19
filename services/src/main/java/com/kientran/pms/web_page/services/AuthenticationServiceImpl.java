package com.kientran.pms.web_page.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kientran.pms.web_page.Utils;
import com.kientran.pms.web_page.security.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

public class AuthenticationServiceImpl implements AuthenticationServiceInterface {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<UserResponse> authenticate(String username, String passwordHash) {


        String currentHost = Utils.getHost();
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

        String currentHost = Utils.getHost();
        URI uriComponents = UriComponentsBuilder.fromHttpUrl(currentHost + "/api/authenticateByToken")
                .queryParam("token", token)
                .build().toUri();

        return restTemplate.getForEntity(uriComponents, UserResponse.class);
    }
}
