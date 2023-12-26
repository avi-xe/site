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
public class FootballYearJsonTests {
    @Autowired
    private JacksonTester<FootballYear> json;

    @Autowired
    private JacksonTester<FootballYear[]> jsonList;

    private FootballYear[] years;

    @BeforeEach
    void setup() {
        years = Arrays.array(
                new FootballYear(1L, "2021-2022"),
                new FootballYear(2L, "2022-2023"),
                new FootballYear(3L, "2023-2024"),
                new FootballYear(4L, "2024-2025"));
    }

    @Test
    void yearSerializationTest() throws IOException {
        FootballYear year = years[0];
        assertThat(json.write(year)).isStrictlyEqualToJson("year/single.json");
        assertThat(json.write(year)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(year)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
        assertThat(json.write(year)).hasJsonPathStringValue("@.name");
        assertThat(json.write(year)).extractingJsonPathStringValue("@.name")
                .isEqualTo("2021-2022");
    }

    @Test
    void yearDeserializationTest() throws IOException {
        String expected = """
                {
                    "id": 99,
                    "name": "2022-2023"
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new FootballYear(99L, "2022-2023"));
        assertThat(json.parseObject(expected).id()).isEqualTo(99L);
        assertThat(json.parseObject(expected).name()).isEqualTo("2022-2023");
    }

    @Test
    void yearListSerializationTest() throws IOException {
        assertThat(jsonList.write(years)).isStrictlyEqualToJson("year/list.json");
    }

    @Test
    void yearListDeserializationTest() throws IOException {
        String expected = """
                [
                    {
                        "id": 1,
                        "name": "2021-2022"
                    },
                    {
                        "id": 2,
                        "name": "2022-2023"
                    },
                    {
                        "id": 3,
                        "name": "2023-2024"
                    },
                    {
                        "id": 4,
                        "name": "2024-2025"
                    }
                ]
                """;
        assertThat(jsonList.parse(expected)).isEqualTo(years);
    }

}
