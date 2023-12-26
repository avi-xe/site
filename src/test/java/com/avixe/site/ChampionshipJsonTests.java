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
public class ChampionshipJsonTests {
    @Autowired
    private JacksonTester<Championship> json;

    @Autowired
    private JacksonTester<Championship[]> jsonList;

    private Championship[] championships;

    @BeforeEach
    void setup() {
        championships = Arrays.array(
                new Championship(1L, "Premier League 21-22", 1L, 2L),
                new Championship(2L, "La Liga 21-22", 2L, 2L),
                new Championship(3L, "Serie A 21-22", 3L, 2L),
                new Championship(4L, "Bundesliga 21-22", 4L, 2L));
    }

    @Test
    void championshipSerializationTest() throws IOException {
        Championship championship = championships[0];
        assertThat(json.write(championship)).isStrictlyEqualToJson("championship/single.json");
        assertThat(json.write(championship)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(championship)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
        assertThat(json.write(championship)).hasJsonPathStringValue("@.name");
        assertThat(json.write(championship)).extractingJsonPathStringValue("@.name")
                .isEqualTo("Premier League 21-22");
        assertThat(json.write(championship)).hasJsonPathNumberValue("@.yearId");
        assertThat(json.write(championship)).extractingJsonPathNumberValue("@.yearId")
                .isEqualTo(2);
        assertThat(json.write(championship)).hasJsonPathNumberValue("@.leagueId");
        assertThat(json.write(championship)).extractingJsonPathNumberValue("@.leagueId")
                .isEqualTo(1);
    }

    @Test
    void championshipDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 99,
                    "name": "Eredivisie 30-31",
                    "leagueId": 99,
                    "yearId": 10
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new Championship(99L, "Eredivisie 30-31", 99L, 10L));
        assertThat(json.parseObject(expected).id()).isEqualTo(99L);
        assertThat(json.parseObject(expected).name()).isEqualTo("Eredivisie 30-31");
        assertThat(json.parseObject(expected).leagueId()).isEqualTo(99L);
        assertThat(json.parseObject(expected).yearId()).isEqualTo(10L);
    }

    @Test
    void championshipListSerializationTest() throws IOException {
        assertThat(jsonList.write(championships)).isStrictlyEqualToJson("championship/list.json");
    }

    @Test
    void championshipListDeserializationTest() throws IOException {
        String expected = """
            [
                {
                    "id": 1,
                    "name": "Premier League 21-22",
                    "leagueId": 1,
                    "yearId": 2
                },
                {
                    "id": 2,
                    "name": "La Liga 21-22",
                    "leagueId": 2,
                    "yearId": 2
                },
                {
                    "id": 3,
                    "name": "Serie A 21-22",
                    "leagueId": 3,
                    "yearId": 2
                },
                {
                    "id": 4,
                    "name": "Bundesliga 21-22",
                    "leagueId": 4,
                    "yearId": 2
                }
            ]
                """;
        assertThat(jsonList.parse(expected)).isEqualTo(championships);
    }

}
