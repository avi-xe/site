package com.avixe.site;

import org.springframework.data.annotation.Id;

public record FootballYear(@Id Long id, String name) {
    
}
