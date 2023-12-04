package com.example.testrest1.JsonParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;

public class Parser {

//    private static final Logger logger = LoggerFactory.getLogger(Parser.class);
    public static final String JSON_PATH = "src/test/java/com/example/testrest1/JsonParser/Ascendex.json";

    public static void main(String[] args) throws FileNotFoundException, ParseException {
//        logger.info(qwerty().toString());
    }

    public static Map<List<String>, List<String>> qwerty() throws FileNotFoundException, ParseException {
        Long i = 1L;
        String exchange = "Ascendex";

        BufferedReader reader = new BufferedReader(new FileReader(JSON_PATH));
        Gson gson = new Gson();
        Type type = new TypeToken<List<Model>>() {
        }.getType();
        List<Model> list = gson.fromJson(reader, type);

        List<String> c = new ArrayList<>();
        List<String> y = new ArrayList<>();

        for (var x : list) {
            y.add(x.getAsset());
        }
        for (var z : list) {
            c.add(z.getTotalBalance());
        }


        Map<List<String>, List<String>> map = new HashMap<>();
        map.put(y, c);
        return map;
    }
}
