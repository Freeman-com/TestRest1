package com.example.testrest1.LOADTESTING;

import com.example.testrest1.model.Status;
import com.example.testrest1.model.User;
import com.example.testrest1.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.*;

public class First_LOADTEST_Class {
    private static UserRepository userRepository;
    private final static int RRS = 1;
    public static String BASE_URL = "http://localhost:8070";
    /**
     * Input:
     * "username" : "555",
     * "email" : "5555",
     * "password" : "5555",
     * "firstName" : "eqw",
     * "lastName" : "321"
     * /==========================/
     * Output:
     * "id": 3,
     * "created": "2023-11-28T19:03:23.319+00:00",
     * "updated": "2023-11-28T19:03:23.319+00:00",
     * "status": "ACTIVE",
     * "username": "555",
     * "firstName": "eqw",
     * "lastName": "321",
     * "email": "5555",
     * "password": "$2a$10$RTgJij3T/k07.ymYfeAuseuWge8FemGa4wpv.ugYzV9biDk3NYUa6",
     * "roles": null
     */
    public static String POST_REGISTRATION_URL = "/api/v1/auth/registration";
    /**
     * Input:
     * "email" : "5244934@gmail.com",
     * "password" : "123"
     */
    public static String GET_LOGIN_URL = "/api/v1/auth/login"; // get Bearer Token (input email/password)
    public static String POST_USER_INFO = "/api/v1/user/{email}"; // for yourself or admin
    private static long idCounter = 5;

    public static <T> T doPost (String path, Class class1) {

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8070/api/v1/auth/registration"))

                .timeout(Duration.ofMinutes(1))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Cache-Control", "no-cache")
                .header("Host", "localhost:8070")
                .header("Connection", "keep-alive")

                .GET()
                .build();
        return null;
    }

    @PostMapping("http://localhost:8070/api/v1/auth/registration")
    public static void registrationUsers() {
        Date now = new Date();
        long id = Long.parseLong(String.valueOf(idCounter++));
        for (int i = 0; i < RRS; i++) {
            User u = new User();
            u.setId(id);
            u.setFirstName("anthony");
            u.setLastName("stain");
            u.setEmail("limpet@gmail.com");
            u.setPassword("12345");
            u.setStatus(Status.ACTIVE);
            u.setCreated(now);
            u.setUpdated(now);
            userRepository.save(u);
        }
    }

    public static void main(String[] args) {
        registrationUsers();
    }

    public static String random(int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.substring(0, length);
    }

    public static void setUserRepository(UserRepository userRepository) {
        First_LOADTEST_Class.userRepository = userRepository;
    }
}
