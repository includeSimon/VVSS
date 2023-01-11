package com.example.collectiveproject.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.sql.Date;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private static final String AUTHORIZATION = "authorization";
    private static final String ROLE_USER = "ROLE_USER";
    private static final String AUTHORITIES = "authorities";
    private static final String ID = "JWT";
    private static final Key secret = MacProvider.generateKey(SignatureAlgorithm.HS512);
    static final byte[] secretBytes = secret.getEncoded();
    private static final String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);
    private static final Long tokenDurationMillis = 10800000L;

    public String getJWTToken(String userName) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(ROLE_USER);
        return Jwts.builder()
                .setId(ID)
                .setSubject(userName)
                .claim(AUTHORITIES,
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenDurationMillis))
                .signWith(SignatureAlgorithm.HS512, base64SecretBytes)
                .compact();
    }
    public static Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(AUTHORIZATION);
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(base64SecretBytes))
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public String getCurrentUserUsername(String token) {
        return decodeJWT(token).getSubject();
    }


    public Claims decodeJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(base64SecretBytes)
                .parseClaimsJws(jwt).getBody();

    }

    public static void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get(AUTHORITIES);

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    public static boolean checkJWTToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION) != null;
    }


}

