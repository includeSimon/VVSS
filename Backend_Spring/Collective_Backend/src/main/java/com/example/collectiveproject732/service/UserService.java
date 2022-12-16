package com.example.collectiveproject732.service;

import com.example.collectiveproject732.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getByUsername(String username);

    boolean addUser(User user);

    User deleteUser(String username);
}
