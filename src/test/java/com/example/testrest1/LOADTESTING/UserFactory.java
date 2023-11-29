package com.example.testrest1.LOADTESTING;

import com.example.testrest1.model.User;

public class UserFactory {

    // Password algorithm generator
    public static String BcryptGenerator() {

    }

    public static String createNewUser(User user) {
        User u = new User();

            u.setId(user.getId());
            u.setUsername(username);
            u.setPassword(password);
            u.setEmail(user.getEmail());
            u.setId(user.getId());
            u.setStatus(user.getStatus());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setCreated(user.getCreated());
            u.setUpdated(user.getUpdated());
    }
}
