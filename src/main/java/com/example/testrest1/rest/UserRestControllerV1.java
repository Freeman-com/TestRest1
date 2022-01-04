package com.example.testrest1.rest;

import com.example.testrest1.model.User;
import com.example.testrest1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserRestControllerV1 {

    private final UserRepository userRepository;


    @Autowired
    public UserRestControllerV1(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "{email}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "email") String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        String password = ((UserDetails)principal).getPassword();

        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(password);
        user1.setEmail(user.getEmail());
        user1.setId(user.getId());
        user1.setStatus(user.getStatus());


//        User result = new User();
//        result.setEmail(user.getEmail());
//        result.setPassword(user.getPassword());
//        result.setRoles(user.getRoles());  RECURSION (STACKOVERFLOW)
//        result.setUsername(user.getUsername());
//        result.setStatus(user.getStatus());


        return new ResponseEntity<>(user1, HttpStatus.OK);

    }
}
