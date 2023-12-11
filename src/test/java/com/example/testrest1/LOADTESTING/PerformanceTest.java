package com.example.testrest1.LOADTESTING;

import com.github.javafaker.Faker;
import net.minidev.json.JSONObject;
import org.apache.http.entity.ContentType;
import org.apache.jmeter.protocol.http.util.HTTPConstants;
import org.junit.jupiter.api.Test;
import us.abstracta.jmeter.javadsl.core.TestPlanStats;

import java.io.IOException;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static us.abstracta.jmeter.javadsl.JmeterDsl.*;

public class PerformanceTest {

    private static final Integer THREADS = 5;
    private static final Integer ITERATIONS = 10;

    @Test
    public void saveAndGetMeasurementsLoadTest() throws IOException {
        TestPlanStats stats = testPlan(
                threadGroup(THREADS, ITERATIONS,

                        httpSampler("http://localhost:8070/api/v1/auth/registration")
                                .method(HTTPConstants.POST)
                                .post(s -> buildRequestBody(), ContentType.APPLICATION_JSON)
                                .children(jsonExtractor("userIdVariable", "userId"))
                                .children(jsonAssertion("dateTime"))

                )
//                , influxDbListener("http://localhost:8086/write?db=jmeter")
//                        .measurement("jmeter")
//                        .application("jmeter")
//                        .token("TOKEN from influxDb")
        ).run();

        assertThat(stats.overall().sampleTimePercentile99()).isLessThan(Duration.ofSeconds(5));
    }

    public static String buildRequestBody() {

        Faker faker = new Faker();
        String username = String.valueOf(faker.name());
        String email = String.valueOf(faker.animal());
        String password = faker.crypto().sha256();


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("email", email);
        jsonObject.put("password", password);

        return jsonObject.toJSONString();
    }
}
