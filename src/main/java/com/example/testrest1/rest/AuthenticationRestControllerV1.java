package com.example.testrest1.rest;

import com.example.testrest1.model.User;
import com.example.testrest1.repository.UserRepository;
import com.example.testrest1.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody User email) {
        try {

            String email1 = email.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email1, email.getPassword()));
            User user = userRepository.findByEmail(email1);

            if (user == null) {
                throw new UsernameNotFoundException("User with email: " + email1 + " not found");
            }

            String token = jwtTokenProvider.createToken(email1, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", email1);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
