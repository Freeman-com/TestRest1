package com.example.testrest1.connections;
import com.google.gson.Gson;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
public class AscendexConnector {
    private static final String secret = "rc7QeKWtZBgVAGb6sxCjhxjZfpAwvXZZs6NXcxuCYjAwy48RkHL5JrZmuCbXtnBY";
    private static final String apiKey = "bdsUsDQtUHfv3Yn5LkSu20DlnmTUrhg0";

    static long timestamp = System.currentTimeMillis();
    String path = "balance";
    /*static <T> void fromArrayToCollection(T[] a, Collection<T> c)*/
    public static <T> T doGet(String path, Class class1) throws IOException, InterruptedException {

        long timestamp = System.currentTimeMillis();
        String msg = timestamp + path;
        path = "balance";
        String result = encode(msg, secret);

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://ascendex.com/0/api/pro/v1/cash/" + path))

                .timeout(Duration.ofMinutes(1))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("x-auth-key", apiKey)
                .header("x-auth-signature", String.valueOf(result))
                .header("x-auth-timestamp", String.valueOf(timestamp))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());


        Gson gson = new Gson();
        T container = gson.fromJson(response.body(), (Type) class1);
        return (T) container;
    }

    public static String encode(String message, String secret) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);
            return new String(Base64.getEncoder().encode(sha256_HMAC.doFinal(message.getBytes())));
        } catch (Exception e) {
            throw new RuntimeException("Unable to sign message.", e);
        }
    }
}
