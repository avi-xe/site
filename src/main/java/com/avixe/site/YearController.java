package com.avixe.site;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/year")
public class YearController {
    private final YearRepository repository;

    public YearController(final YearRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<Year> findById(@PathVariable Long id) {
        Optional<Year> yearOptional = repository.findById(id);
        if (yearOptional.isPresent()) {
            return ResponseEntity.ok(yearOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
