package com.avixe.site;

import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/match")
public class MatchController {
    private final MatchRepository repository;

    public MatchController(final MatchRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Match> findById(@PathVariable String id) {
        Optional<Match> matchOptional = repository.findById(id);
        if (matchOptional.isPresent()) {
            return ResponseEntity.ok(matchOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
