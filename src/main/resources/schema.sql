CREATE TABLE league (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(256) NOT NULL
);
CREATE TABLE match (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(256) NOT NULL
);
CREATE TABLE football_year (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(256) NOT NULL
);
CREATE TABLE championship (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME VARCHAR(256) NOT NULL,
    LEAGUE_ID BIGINT NOT NULL,
    YEAR_ID BIGINT NOT NULL,
    foreign key (LEAGUE_ID) references league(ID),
    foreign key (YEAR_ID) references football_year(ID)
);