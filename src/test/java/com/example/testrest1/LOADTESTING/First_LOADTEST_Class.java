package com.example.testrest1.LOADTESTING;

import com.example.testrest1.model.User;

public class First_LOADTEST_Class {

    public static String BASE_URL = "http://localhost:8070";

    /**
     * Input:
     *     "username" : "555",
     *     "email" : "5555",
     *     "password" : "5555",
     *     "firstName" : "eqw",
     *     "lastName" : "321"
     *     /==========================/
     *     Output:
     *     "id": 3,
     *     "created": "2023-11-28T19:03:23.319+00:00",
     *     "updated": "2023-11-28T19:03:23.319+00:00",
     *     "status": "ACTIVE",
     *     "username": "555",
     *     "firstName": "eqw",
     *     "lastName": "321",
     *     "email": "5555",
     *     "password": "$2a$10$RTgJij3T/k07.ymYfeAuseuWge8FemGa4wpv.ugYzV9biDk3NYUa6",
     *     "roles": null
     */
    public static  String POST_REGISTRATION_URL = "/api/v1/auth/registration";

    /**
     * Input:
     *     "email" : "5244934@gmail.com",
     *     "password" : "123"
     */
    public static String GET_LOGIN_URL = "/api/v1/auth/login"; // get Bearer Token (input email/password)
    public static  String POST_USER_INFO = "/api/v1/user/{email}"; // for yourself or admin

    public static String generateUsers(User user) {

        User user1 = new User();
        user1.setId(user1.getId());
        user1.setUsername(username);
        user1.setPassword(password);
        user1.setEmail(user.getEmail());
        user1.setId(user.getId());
        user1.setStatus(user.getStatus());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setCreated(user.getCreated());
        user1.setUpdated(user.getUpdated());

    }

    public static void main(String[] args) {



    }
}
