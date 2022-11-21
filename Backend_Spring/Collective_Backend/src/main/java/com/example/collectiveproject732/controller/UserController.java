package com.example.collectiveproject732.controller;

import com.example.collectiveproject732.dto.UserDTO;
import com.example.collectiveproject732.model.User;
import com.example.collectiveproject732.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
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
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        newUser.setPassword(encodedPassword);
        boolean added = userService.addUser(newUser);
        if (!added)
            return new ResponseEntity<Object>(CustomResponseEntity.getError("An error has occurred while attempting to add the new user"), HttpStatus.OK);
        else
            return new ResponseEntity<Object>(CustomResponseEntity.getMessage("New user successfully added!"), HttpStatus.OK);
    }

    @DeleteMapping("/users/delete/{username}")
    ResponseEntity<Object> deleteUser(@PathVariable String username) {
        User deletedUser = userService.deleteUser(username);
        if (deletedUser == null) {
            return new ResponseEntity<Object>(CustomResponseEntity.getError("An error occurred while attempting to delete the user."), HttpStatus.OK);
        }
        return new ResponseEntity<Object>(CustomResponseEntity.getMessage("User successfully deleted!"), HttpStatus.OK);
    }
}
