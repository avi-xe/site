package com.avixe.site;

import org.springframework.data.annotation.Id;

public record League(@Id Long id, String name) {

}
