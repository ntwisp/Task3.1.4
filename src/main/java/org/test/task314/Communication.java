package org.test.task314;

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
public class Communication {

    private final RestTemplate restTemplate = new RestTemplate();
    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final String URL = "http://94.198.50.185:7081/api/users";
    private String SESSION_ID;

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        List<User> allUsers = responseEntity.getBody();
        List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
        SESSION_ID = cookies.get(0).substring(0, cookies.get(0).indexOf(';'));
        return allUsers;
    }

    public String saveUser(User user) {
        httpHeaders.set("Cookie", SESSION_ID);
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        System.out.println("Code Part 1: " + response.getBody());
        return response.getBody();
    }

    public String updateUser(User user) {
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        System.out.println("Code Part 2: " + response.getBody());
        return response.getBody();
    }

    public String deleteUser(Long id) {
        HttpEntity<User> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.println("Code Part 3: " + response.getBody());
        return response.getBody();
    }
}