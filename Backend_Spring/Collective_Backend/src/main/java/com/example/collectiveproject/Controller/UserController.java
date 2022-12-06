package com.example.collectiveproject.Controller;

import com.example.collectiveproject.Model.User;
import com.example.collectiveproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("api/users")
@CrossOrigin("localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/find-all")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(this.userService.findAll());
    }

    @GetMapping("/find-by-id/{id}")
    public User findUserById(@PathVariable(value="id") long id) {
        return userService.findById(id);
    }
}
