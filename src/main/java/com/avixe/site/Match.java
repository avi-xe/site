package com.avixe.site;

import org.springframework.data.annotation.Id;

public record Match(@Id Long id, Long homeId, Long awayId, Long matchweekId) {
}
