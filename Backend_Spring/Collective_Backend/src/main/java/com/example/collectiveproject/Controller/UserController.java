package com.example.collectiveproject.Controller;

import com.example.collectiveproject.Model.DTO.UserDTO;
import com.example.collectiveproject.Model.User;
import com.example.collectiveproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Controller
@RequestMapping("api/users")
@CrossOrigin("localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/find-all")
    public List<UserDTO> findAll(){
        return userService.findAll().stream().map(User::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/find-by-id/{id}")
    public User findUserById(@PathVariable(value="id") long id) {
        return userService.findById(id);
    }

    @GetMapping("/find-by-username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(this.userService.findByUsername(username));
    }
}
