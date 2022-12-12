package com.example.collectiveproject.Service;

import com.example.collectiveproject.Model.User;
import com.example.collectiveproject.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return this.userRepository.findAll();
    }

    public User findById(long id) {
        Optional<User> foundUser = this.userRepository.findById(id);
        return foundUser.orElse(null);
    }
}
