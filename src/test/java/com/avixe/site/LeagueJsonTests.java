package com.avixe.site;

import java.io.IOException;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class LeagueJsonTests {
    @Autowired
    private JacksonTester<League> json;

    @Autowired
    private JacksonTester<League[]> jsonList;

    private League[] leagues;

    @BeforeEach
    void setup() {
        leagues = Arrays.array(
                new League(1L, "Premier League"),
                new League(2L, "La Liga"),
                new League(3L, "Serie A"),
                new League(4L, "Bundesliga"));
    }

    @Test
    void leagueSerializationTest() throws IOException {
        League league = leagues[0];
        assertThat(json.write(league)).isStrictlyEqualToJson("league/single.json");
        assertThat(json.write(league)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(league)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
        assertThat(json.write(league)).hasJsonPathStringValue("@.name");
        assertThat(json.write(league)).extractingJsonPathStringValue("@.name")
                .isEqualTo("Premier League");
    }

    @Test
    void leagueDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 99,
                    "name": "Eredivisie"
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new League(99L, "Eredivisie"));
        assertThat(json.parseObject(expected).id()).isEqualTo(99L);
        assertThat(json.parseObject(expected).name()).isEqualTo("Eredivisie");
    }

    @Test
    void leagueListSerializationTest() throws IOException {
        assertThat(jsonList.write(leagues)).isStrictlyEqualToJson("league/list.json");
    }

    @Test
    void leagueListDeserializationTest() throws IOException {
        String expected = """
                [
                    {
                        "id": 1,
                        "name": "Premier League"
                    },
                    {
                        "id": 2,
                        "name": "La Liga"
                    },
                    {
                        "id": 3,
                        "name": "Serie A"
                    },
                    {
                        "id": 4,
                        "name": "Bundesliga"
                    }
                ]
                """;
        assertThat(jsonList.parse(expected)).isEqualTo(leagues);
    }

}
