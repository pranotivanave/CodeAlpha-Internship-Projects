package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.Itinerary;
import com.example.service.ItineraryService;


@RestController
@RequestMapping("/api/itinerary")
public class ItineraryController {

    private final ItineraryService itineraryService;

    @Autowired
    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Itinerary>> getItinerariesByUser(@PathVariable Long userId) {
        List<Itinerary> itineraries = itineraryService.getItinerariesByUser(userId);
        if (itineraries.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itineraries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Itinerary> getItineraryById(@PathVariable Long id) {
        Itinerary itinerary = itineraryService.getItineraryById(id);
        if (itinerary == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itinerary);
    }

    @PostMapping
    public ResponseEntity<String> createItinerary(@RequestBody Itinerary itinerary) {
        String message = itineraryService.createItinerary(itinerary);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Itinerary> updateItinerary(@PathVariable Long id, @RequestBody Itinerary itinerary) {
        itinerary.setId(id);  // Ensure the ID is set for update
        Itinerary updatedItinerary = itineraryService.updateItinerary(itinerary);
        return ResponseEntity.ok(updatedItinerary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItinerary(@PathVariable Long id) {
        itineraryService.deleteItinerary(id);
        return ResponseEntity.noContent().build();
    }
}
