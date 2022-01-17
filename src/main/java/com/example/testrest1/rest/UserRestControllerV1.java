package com.example.testrest1.rest;

import com.example.testrest1.connections.AscendexConnectorTest;
import com.example.testrest1.model.User;
import com.example.testrest1.repository.AscendexRepository;
import com.example.testrest1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserRestControllerV1 {

    private final UserRepository userRepository;
    private final AscendexRepository ascendexRepository;

    @Autowired
    public UserRestControllerV1(UserRepository userRepository, AscendexRepository ascendexRepository) {
        this.userRepository = userRepository;
        this.ascendexRepository = ascendexRepository;
    }

    @GetMapping(value = "{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(name = "email") String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        String password = ((UserDetails) principal).getPassword();

        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(password);
        user1.setEmail(user.getEmail());
        user1.setId(user.getId());
        user1.setStatus(user.getStatus());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setCreated(user.getCreated());
        user1.setUpdated(user.getUpdated());

//        user1.setRoles(new ArrayList<>(user.getRoles()));
//        user1.setRoles(user.getRoles());  RECURSION (STACKOVERFLOW)
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }

    @GetMapping(value = "maintable/{email}")
    public String getTableByUser(@PathVariable(name = "email") String email) throws IOException, InterruptedException {

        User user = userRepository.findByEmail(email);

        List<String> list = new ArrayList<>();
        List<Integer> groupList = new ArrayList<>();

        var keyList = ascendexRepository.findByUsersId(user.getId());

        Set<Object> set = new HashSet<>();

        for (var j : keyList) {
            list.add(new AscendexConnectorTest().senderMethod(j.getApiKey(), j.getSecret(), j.getGroup()));
        }


        return String.valueOf(list);
    }
}
//        Map<Object, Object> hashMap = new HashMap<>();
//
//        for (var z : keyList) {
//            hashMap.put(z.getApiKey(), z.getSecret());
//
//        }
//        for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
//
//            list.add(new AscendexConnectorTest().senderMethod((String) entry.getKey(), (String) entry.getValue(), 3));
//
//        }