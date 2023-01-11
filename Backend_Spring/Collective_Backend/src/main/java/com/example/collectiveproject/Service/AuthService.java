package com.example.collectiveproject.Service;

import com.example.collectiveproject.Exceptions.ErrorCode;
import com.example.collectiveproject.Exceptions.IncorrectCredentialsException;
import com.example.collectiveproject.Exceptions.UsernameNotFoundException;
import com.example.collectiveproject.Exceptions.UsernameTakenException;
import com.example.collectiveproject.Model.User;
import com.example.collectiveproject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String login(String userName, String password)
            throws UsernameNotFoundException, IncorrectCredentialsException {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                user.setToken(tokenService.getJWTToken(userName));
                return this.userRepository.save(user).getToken();
            }

            throw new IncorrectCredentialsException("Incorrect password.", ErrorCode.INCORRECT_PASSWORD);
        }
        throw new UsernameNotFoundException("Username " + userName + " not found. No account? Register first.", ErrorCode.USERNAME_NOT_FOUND);
    }


    public void logout() throws UsernameNotFoundException {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails user) {
            userName = user.getUsername();
        } else {
            userName = principal.toString();
        }
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setToken(null);
            userRepository.save(user);
            return;
        }
        throw new UsernameNotFoundException("Username " + userName + " not found.", ErrorCode.USERNAME_NOT_FOUND);
    }

    public User registerUser(User user) throws UsernameTakenException {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            throw new UsernameTakenException("Username " + user.getUserName() + " already taken.", ErrorCode.USERNAME_TAKEN);
        }
        String encodedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user = userRepository.save(user);
        return user;
    }
}

