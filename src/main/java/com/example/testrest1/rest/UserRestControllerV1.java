package com.example.testrest1.rest;

import com.binance.connector.client.impl.SpotClientImpl;
import com.example.testrest1.connections.AscendexConnectorTest;
import com.example.testrest1.model.User;
import com.example.testrest1.model.exchanges.Binance;
import com.example.testrest1.repository.AscendexRepository;
import com.example.testrest1.repository.BinanceRepository;
import com.example.testrest1.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserRestControllerV1 {

    public static final String JSON_PATH = "src/main/resources/jsons/buffer.json";

    private final UserRepository userRepository;
    private final AscendexRepository ascendexRepository;
    private final BinanceRepository binanceRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserRestControllerV1.class);

    @Autowired
    public UserRestControllerV1(UserRepository userRepository, AscendexRepository ascendexRepository, BinanceRepository binanceRepository) {
        this.userRepository = userRepository;
        this.ascendexRepository = ascendexRepository;
        this.binanceRepository = binanceRepository;
    }

    @PostMapping(value = "{email}")
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
    public String getTableByUser(@PathVariable(name = "email") String email) throws Exception {

        AscendexConnectorTest ascendexConnectorTest = new AscendexConnectorTest();
        User user = userRepository.findByEmail(email);
        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson();


        var keyList = ascendexRepository.findByUsersId(user.getId());
        var keyList2 = binanceRepository.findByUsersId(user.getId());

        for (var j : keyList) {
            var x = ascendexConnectorTest.senderMethod(j.getApiKey(), j.getSecret(), j.getGroup());
        }

        /* Account Snapshot - Binance */

        LinkedHashMap<String, Object> parameters = new LinkedHashMap<>();
        parameters.put("type", "SPOT");

        List<String> test = new ArrayList<>();
        Model model = new Model();
        JSONObject object = new JSONObject();

        for (var e : keyList2) {
            SpotClientImpl client = new SpotClientImpl(e.getApiKey(), e.getSecret());
            String result = client.createWallet().accountSnapshot(parameters);

            var c = objectMapper.readValue(result, Model[].class);

            for (var f : c) {
                test.add(String.valueOf(f.getSnapshotVos()));
            }
//            test.add(String.valueOf(c.getCode()));
//            test.add(String.valueOf(c.getSnapshotVos()));

            if (!result.isEmpty()) {
                try {
                    objectMapper.writeValue(new File(
                            "/home/tony/IdeaProjects/TestRest1/src/main/resources/jsons/buffer.json"), test);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        /* END  Account Snapshot - Binance */

        return String.valueOf(test);
    }
}

//    Gson gson = new Gson();
//    Type type = new TypeToken<List<Model>>() {
//    }.getType();
//    List<Model> c = gson.fromJson(String.valueOf(list1), type);
//    List<String> strings = new ArrayList<>();
//        for (var x : c) {
//                strings.add(x.getBalances());
//                strings.add(x.getAsset());
//                strings.add(x.getAvailableBalance());
//                }


/*-------------------------------------------------------------------------------------------------------*/


//            ObjectMapper d = new ObjectMapper();
//            var map = d.readValue(x, Map.class);
//            map.put("email", j.getEmail());

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