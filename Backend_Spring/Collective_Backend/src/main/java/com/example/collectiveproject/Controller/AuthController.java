package com.example.collectiveproject.Controller;

import com.example.collectiveproject.Exceptions.IncorrectCredentialsException;
import com.example.collectiveproject.Exceptions.UsernameNotFoundException;
import com.example.collectiveproject.Exceptions.UsernameTakenException;
import com.example.collectiveproject.Model.Token;
import com.example.collectiveproject.Model.User;
import com.example.collectiveproject.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class AuthController {
    private static final String AUTH_PATH = "/auth";
    private static final String LOGIN_PATH = "/login";
    private static final String LOGOUT_PATH = "/logout";
    private static final String REGISTER_PATH = "/register";
    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "password";
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(AUTH_PATH + LOGIN_PATH)
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new Token(this.authService.login(credentials.get(USER_NAME), credentials.get(PASSWORD))));
        } catch (UsernameNotFoundException | IncorrectCredentialsException exception) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(exception);
        }
    }

    @PostMapping(AUTH_PATH + LOGOUT_PATH)
    public ResponseEntity<?> logout() {
        try {
            this.authService.logout();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(null);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e);
        }
    }

    @PostMapping(REGISTER_PATH)
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(authService.registerUser(user));
        } catch (UsernameTakenException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(e);
        }
    }

}

