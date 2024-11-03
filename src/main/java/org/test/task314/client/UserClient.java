package org.test.task314.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.test.task314.model.User;

import java.util.List;

@Component
public class UserClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final String URL = "http://94.198.50.185:7081/api/users";
    private String SESSION_ID;

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        SESSION_ID = extractSessionId(responseEntity);
        return responseEntity.getBody();
    }

    public String saveUser(User user) {
        httpHeaders.set("Cookie", SESSION_ID);
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class).getBody();
    }

    public String updateUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class).getBody();
    }

    public String deleteUser(Long id) {
        HttpEntity<User> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class).getBody();
    }

    private String extractSessionId(ResponseEntity<?> responseEntity) {
        List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
        return cookies != null ? cookies.get(0).split(";")[0] : null;
    }
}