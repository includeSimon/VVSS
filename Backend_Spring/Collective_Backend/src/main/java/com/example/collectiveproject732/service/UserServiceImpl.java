package com.example.collectiveproject732.service;

import com.example.collectiveproject732.model.User;
import com.example.collectiveproject732.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUserName(username).orElse(null);
    }

    // todo: check for violations of user fields
    @Override
    public boolean addUser(User user) {
        this.userRepository.save(user);
        return true;
    }

    @Override
    public User deleteUser(String username) {
        Optional<User> userOptional = userRepository.findByUserName(username);
        userOptional.ifPresent(user -> userRepository.deleteById(user.getId()));
        return userOptional.orElse(null);
    }
}
