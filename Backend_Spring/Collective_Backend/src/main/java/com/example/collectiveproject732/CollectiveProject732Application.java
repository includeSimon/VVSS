package com.example.collectiveproject732;

import com.example.collectiveproject732.model.User;
import com.example.collectiveproject732.model.UserRole;
import com.example.collectiveproject732.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CollectiveProject732Application {

    public static void main(String[] args) {
        SpringApplication.run(CollectiveProject732Application.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.addUser(
                    User.builder()
                            .userName("Johnny")
                            .firstName("John")
                            .lastName("Varvatos")
                            .email("V@gmail.com")
                            .password("password")
                            .userRole(UserRole.USER)
                            .build()
            );

            userService.addUser(
                    User.builder()
                            .userName("Maria")
                            .firstName("Johnny")
                            .lastName("Cash")
                            .email("C@gmail.com")
                            .password("1234")
                            .userRole(UserRole.USER)
                            .build()
            );

            userService.addUser(
                    User.builder()
                            .userName("JohnCena")
                            .firstName("John")
                            .lastName("Cena")
                            .email("JC@gmail.com")
                            .password("1234")
                            .userRole(UserRole.ADMIN)
                            .build()
            );
        };
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
