package com.avixe.site;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/championship")
public class ChampionshipController {
    private final ChampionshipRepository repository;

    public ChampionshipController(final ChampionshipRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Championship> findById(@PathVariable Long id) {
        Optional<Championship> championshipOptional = repository.findById(id);
        if (championshipOptional.isPresent()) {
            return ResponseEntity.ok(championshipOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
