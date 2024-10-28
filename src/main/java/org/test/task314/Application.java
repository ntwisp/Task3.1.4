package org.test.task314;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.test.task314.model.User;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Communication communication = new Communication();
        List<User> allUsers = communication.getAllUsers();
        StringBuilder code = new StringBuilder();
        User newUser = new User(3L, "James", "Brown", (byte) 25);
        code.append(communication.saveUser(newUser));
        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        code.append(communication.updateUser(newUser));
        code.append(communication.deleteUser(3L));
        System.out.println("Code: " + code);
    }
}
