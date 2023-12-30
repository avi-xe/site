package com.avixe.site;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FootballYearTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAFootballYearWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("avi-xe", "abc123")
                .getForEntity("/football-year/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(1);

        String amount = documentContext.read("$.name");
        assertThat(amount).isEqualTo("2020-2021");
    }

    @Test
    void shouldNotReturnAFootballYearWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("avi-xe", "abc123")
                .getForEntity("/football-year/99", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

    @Test
    void shouldReturnAllCashCardsWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("avi-xe", "abc123")
                .getForEntity("/football-year", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int cashCardCount = documentContext.read("$.length()");
        assertThat(cashCardCount).isEqualTo(4);

        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(1, 2, 3, 4);

        JSONArray amounts = documentContext.read("$..name");
        assertThat(amounts).containsExactlyInAnyOrder("2020-2021", "2021-2022", "2022-2023", "2023-2024");
    }

}
