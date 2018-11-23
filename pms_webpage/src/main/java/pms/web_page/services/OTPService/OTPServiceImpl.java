package pms.web_page.services.OTPService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pms.web_page.Utils;

import java.net.URI;
import java.util.HashMap;

public class OTPServiceImpl implements OTPServiceInterface {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<CreatingOTPResponse> createOTP(String token) {
        String currentHost = Utils.getWebServiceDomain();
        URI uriComponents = UriComponentsBuilder.fromHttpUrl(currentHost + "/api/otp")
                .queryParam("token", token)
                .build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        return restTemplate.exchange(uriComponents, HttpMethod.POST, entity, CreatingOTPResponse.class);
    }

    @Override
    public ResponseEntity<VerifyingOTPResponse> verifyOTP(String token, String otp) {

        String currentHost = Utils.getWebServiceDomain();
        URI uriComponents = UriComponentsBuilder.fromHttpUrl(currentHost + "/api/otp/verify")
                .queryParam("token", token)
                .queryParam("otp", otp)
                .build().toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>("", headers);

        return restTemplate.exchange(uriComponents, HttpMethod.POST, entity, VerifyingOTPResponse.class);
    }
}

