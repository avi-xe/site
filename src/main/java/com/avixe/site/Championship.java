package com.avixe.site;

import org.springframework.data.annotation.Id;

public record Championship(@Id Long id, String name, Long leagueId, long yearId) {
    
}
