package pms.web_page.services.PasswordManager;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.thymeleaf.expression.Lists;
import org.thymeleaf.util.ListUtils;
import pms.web_page.Utils;
import pms.web_page.services.OTPService.CreatingOTPResponse;
import pms.web_page.services.OTPService.VerifyingOTPResponse;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordManagementServiceImpl implements PasswordManagementServiceInterface {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<List<PasswordResponse>> getAll(String username, String domain) {
        String currentHost = Utils.getPMSServiceDomain();

        URI uriComponents = UriComponentsBuilder.fromHttpUrl(currentHost + "/api/passwords")
                .queryParam("username", username)
                .queryParam("domain", domain)
                .build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        ResponseEntity<PasswordResponse[]> responseEntity = restTemplate.exchange(uriComponents, HttpMethod.GET, entity, PasswordResponse[].class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            List<PasswordResponse> list = Arrays.asList(responseEntity.getBody());
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<PasswordResponse>>(new ArrayList<>(), responseEntity.getStatusCode());
        }
    }

    @Override
    public ResponseEntity<PasswordResponse> createPassword(CreatePasswordRequestParam request) throws InvalidArgumentException {
        String currentHost = Utils.getPMSServiceDomain();

        URI uriComponents = UriComponentsBuilder.fromHttpUrl(currentHost + "/api/passwords")
                .queryParam("request", request)
                .build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        return restTemplate.exchange(uriComponents, HttpMethod.POST, entity, PasswordResponse.class);
    }

    @Override
    public ResponseEntity<PasswordResponse> updatePassword(Long id, UpdatePasswordRequestParam request) throws InvalidArgumentException {
        String currentHost = Utils.getPMSServiceDomain();

        URI uriComponents = UriComponentsBuilder.fromHttpUrl(currentHost + "/api/passwords" + "/" + id)
                .queryParam("request", request)
                .build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        return restTemplate.exchange(uriComponents, HttpMethod.POST, entity, PasswordResponse.class);
    }

    @Override
    public ResponseEntity<String> deletePassword(Long id) {
        String currentHost = Utils.getPMSServiceDomain();

        URI uriComponents = UriComponentsBuilder.fromHttpUrl(currentHost + "/api/passwords" + "/" + id)
                .build().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        return restTemplate.exchange(uriComponents, HttpMethod.POST, entity, String.class);
    }
}

