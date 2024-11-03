package org.test.task314;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.test.task314.model.User;
import org.test.task314.service.UserService;

import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext context) {
        return args -> {
            UserService userService = context.getBean(UserService.class);
            List<User> allUsers = userService.getAllUsers();

            StringBuilder code = new StringBuilder();
            User newUser = new User(3L, "James", "Brown", (byte) 25);

            code.append(userService.saveUser(newUser));
            newUser.setName("Thomas");
            newUser.setLastName("Shelby");
            code.append(userService.updateUser(newUser));
            code.append(userService.deleteUser(3L));

            System.out.println("Code: " + code);
        };
    }
}