package com.avixe.site;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/championship")
public class ChampionshipController {
    private final ChampionshipRepository championshipRepository;

    public ChampionshipController(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Championship> findById(@PathVariable Long id) {
        Optional<Championship> championshipOptional = championshipRepository.findById(id);
        if (championshipOptional.isPresent()) {
            return ResponseEntity.ok(championshipOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/")
    private ResponseEntity<Iterable<Championship>> findAll() {
        Iterable<Championship> championships = championshipRepository.findAll();
        return ResponseEntity.ok(championships);
    }

    @GetMapping("/by-year/{requestedId}")
    public ResponseEntity<Iterable<Championship>> findAllByYearId(@PathVariable Long id) {
        Iterable<Championship> championships = championshipRepository.findAllByYearId(id);
        return ResponseEntity.ok(championships);
    }

    @GetMapping("/by-league/{requestedId}")
    public ResponseEntity<Iterable<Championship>> findAllByLeagueId(@PathVariable Long id) {
        Iterable<Championship> championships = championshipRepository.findAllByLeagueId(id);
        return ResponseEntity.ok(championships);
    }

}
