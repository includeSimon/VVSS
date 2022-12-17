package com.example.collectiveproject.Security;


import com.example.collectiveproject.Model.User;
import com.example.collectiveproject.Repository.UserRepository;
import com.example.collectiveproject.Service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private static final String AUTHORITIES = "authorities";

    private final UserRepository userRepository;

    @Autowired
    public JWTAuthorizationFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        try {
            if (TokenService.checkJWTToken(request)) {

                Claims claims = TokenService.validateToken(request);

                String userName = claims.getSubject();
                Optional<User> optionalUser = userRepository.findByUserName(userName);
                User user = optionalUser.get();
                if (user.getToken() == null) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }

                if (claims.get(AUTHORITIES) != null) {
                    TokenService.setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }
}
