package com.avixe.site;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/league")
public class LeagueController {
    private final LeagueRepository repository;

    public LeagueController(final LeagueRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<League> findById(@PathVariable Long id) {
        Optional<League> leagueOptional = repository.findById(id);
        if (leagueOptional.isPresent()) {
            return ResponseEntity.ok(leagueOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
