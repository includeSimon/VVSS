package com.example.collectiveproject732.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.collectiveproject732.dto.UserDTO;
import com.example.collectiveproject732.model.CustomResponseEntity;
import com.example.collectiveproject732.model.User;
import com.example.collectiveproject732.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(ModelMapper modelMapper,
                          UserService userService,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @GetMapping("/users")
    List<UserDTO> getUsers() {
        return userService.getAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{username}")
    UserDTO getUserByUsername(@PathVariable String username) {
        User user = userService.getByUsername(username);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @PostMapping("/users")
    ResponseEntity<Object> addUser(@RequestBody UserDTO userDTO) {
        User newUser = modelMapper.map(userDTO, User.class);
        String encodedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
        newUser.setPassword(encodedPassword);
        boolean added = userService.addUser(newUser);
        if (!added)
            return new ResponseEntity<>(CustomResponseEntity.getError("An error has occurred while attempting to add the new user"), HttpStatus.OK);
        else
            return new ResponseEntity<>(CustomResponseEntity.getMessage("New user successfully added!"), HttpStatus.OK);
    }

    @DeleteMapping("/users/delete/{username}")
    ResponseEntity<Object> deleteUser(@PathVariable String username) {
        User deletedUser = userService.deleteUser(username);
        if (deletedUser == null) {
            return new ResponseEntity<>(CustomResponseEntity.getError("An error occurred while attempting to delete the user."), HttpStatus.OK);
        }
        return new ResponseEntity<>(CustomResponseEntity.getMessage("User successfully deleted!"), HttpStatus.OK);
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getByUsername(username);

                String accessToken = JWT.create()
                        .withSubject(user.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000)) // 10min
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole().toString())))
                        .sign(algorithm);

                String refreshToken = JWT.create()
                        .withSubject(user.getUserName())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 180 * 60 * 1000)) // 3hours
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", Collections.singletonList(new SimpleGrantedAuthority(user.getUserRole().toString())))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("accessToken", accessToken);
                tokens.put("refreshToken", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
