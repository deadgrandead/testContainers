package com.deadgrandead.testcontainers;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestContainersApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    static GenericContainer<?> devApp = new GenericContainer<>("devapp:latest").withExposedPorts(8080);
    static GenericContainer<?> prodApp = new GenericContainer<>("prodapp:latest").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void devAppTest() {
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity("http://localhost:" + devApp.getMappedPort(8080) + "/profile", String.class);
        assertEquals("Current profile is dev", forEntity.getBody());
    }

    @Test
    void prodAppTest() {
        ResponseEntity<String> forEntity = testRestTemplate.getForEntity("http://localhost:" + prodApp.getMappedPort(8081) + "/profile", String.class);
        assertEquals("Current profile is production", forEntity.getBody());
    }
}
