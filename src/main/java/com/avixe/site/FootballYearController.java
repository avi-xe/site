package com.avixe.site;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/football-year")
public class FootballYearController {
    private final FootballYearRepository repository;

    public FootballYearController(FootballYearRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    private ResponseEntity<Iterable<FootballYear>> findAll() {
        Iterable<FootballYear> years = repository.findAll();
        return ResponseEntity.ok(years);
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<FootballYear> findById(@PathVariable Long id) {
        Optional<FootballYear> yearOptional = repository.findById(id);
        if (yearOptional.isPresent()) {
            return ResponseEntity.ok(yearOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
