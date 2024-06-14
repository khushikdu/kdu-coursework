package com.kdu.smarthome.controllers;

import com.kdu.smarthome.model.Users;
import com.kdu.smarthome.services.UserService;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final UserService personService;

    @Autowired
    public AuthController(UserService personService){
        this.personService = personService;
    }

    public static final String JWT_KEY = "jxgEQeXHuPq8VdbyYFNkANdudQ53YUn4";
    public static final String JWT_HEADER = "Authorization";
    private String jwt;

    /**
     * to register the user and generate the token
     * @param person: user object
     * @param response: response to be generated
     * @return : the response entity object
     * throws Exception
     */
    @PostMapping("/auth/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Users person, HttpServletResponse response) throws Exception {
        personService.addUser(person);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
        jwt = Jwts.builder().issuer("kdu").subject("JWT Token")
                .claim("username", authentication.getName())
                .claim("roles", populateAuthorities(authentication.getAuthorities()))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 30000000))
                .signWith(key).compact();
        Map<String, String> responsebody = new HashMap<>();
        responsebody.put("message", "User registered successfully.");
        responsebody.put("token", jwt);

        return new ResponseEntity<>(responsebody, HttpStatus.OK);
    }

    /**
     * to filter the generated token
     * @param request: the request object of the user
     * @param response: the response object for the user
     * @param filterChain: to perform the filtration operation
     * throws ServletException
     * throws IOException
     */
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
            jwt = Jwts.builder().issuer("kdu").subject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("roles", populateAuthorities(authentication.getAuthorities()))
                    .issuedAt(new Date())
                    .expiration(new Date((new Date()).getTime() + 30000000))
                    .signWith(key).compact();
            response.setHeader(JWT_HEADER, jwt);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * to populate authorities to the user
     * @param collection: to form a collection of the granted authorities
     * @return : String set of authorities
     */
    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

}