package com.avixe.site;

import java.util.Optional;
import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchController {
    private final MatchRepository matchRepository;

    public MatchController(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Match> findById(@PathVariable Long requestedId, Principal principal) {
        Optional<Match> matchOptional = matchRepository.findById(requestedId);
        if (matchOptional.isPresent()) {
            return ResponseEntity.ok(matchOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
