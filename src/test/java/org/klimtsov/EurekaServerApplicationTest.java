package org.klimtsov;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EurekaServerApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        //Простейший тест на загрузку контекста.
    }

    @Test
    void eurekaDashboardIsAccessible() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Eureka");
    }

    @Test
    void actuatorHealthEndpointWorks() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/actuator/health", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("\"status\":\"UP\"");
    }

    @Test
    void eurekaServerAcceptsRegistrations() {
        //Проверяем, что Eureka REST API доступен.
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/eureka/apps", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}