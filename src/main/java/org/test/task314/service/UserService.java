package org.test.task314.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.task314.client.UserClient;
import org.test.task314.model.User;

import java.util.List;

@Service
public class UserService {

    private final UserClient userClient;

    @Autowired
    public UserService(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<User> getAllUsers() {
        return userClient.getAllUsers();
    }

    public String saveUser(User user) {
        return userClient.saveUser(user);
    }

    public String updateUser(User user) {
        return userClient.updateUser(user);
    }

    public String deleteUser(Long id) {
        return userClient.deleteUser(id);
    }
}
