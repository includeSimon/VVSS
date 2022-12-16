package com.example.collectiveproject732.service;

import com.example.collectiveproject732.model.User;
import com.example.collectiveproject732.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final static String USER_NOT_FOUND_MESSAGE = "Unable to find username %s";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() ->
                new UsernameNotFoundException(
                     String.format(USER_NOT_FOUND_MESSAGE, username)
                )
        );
    }

    @Override
    public boolean addUser(User user) {
        log.info("Saving new User {} with password {} to the database", user.getUserName(), user.getPassword());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return true;
    }

    @Override
    public User deleteUser(String username) {
        Optional<User> userOptional = userRepository.findByUserName(username);
        userOptional.ifPresent(user -> userRepository.deleteById(user.getId()));
        return userOptional.orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getByUsername(username);
        GrantedAuthority authorization = new SimpleGrantedAuthority(user.getUserRole().toString());

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), Collections.singletonList(authorization));
    }
}
