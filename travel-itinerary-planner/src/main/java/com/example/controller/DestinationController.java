package com.example.controller;


import java.util.List;
import java.util.Optional;


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

import com.example.model.Destination;
import com.example.service.DestinationService;

@RestController
	@RequestMapping("/api/destinations")
	public class DestinationController {

	    private final DestinationService destinationService;

	    @Autowired
	    public DestinationController(DestinationService destinationService) {
	        this.destinationService = destinationService;
	    }

	    // Create a new Destination
	    @PostMapping
	    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination) {
	        Destination createdDestination = destinationService.createDestination(destination);
	        return new ResponseEntity<>(createdDestination, HttpStatus.CREATED);
	    }

	    // Get all Destinations
	    @GetMapping
	    public ResponseEntity<List<Destination>> getAllDestinations() {
	        List<Destination> destinations = destinationService.getAllDestinations();
	        return new ResponseEntity<>(destinations, HttpStatus.OK);
	    }

	    // Get a single Destination by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Destination> getDestinationById(@PathVariable Long id) {
	        Optional<Destination> destination = destinationService.getDestinationById(id);
	        if (destination.isPresent()) {
	            return new ResponseEntity<>(destination.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Update an existing Destination
	    @PutMapping("/{id}")
	    public ResponseEntity<Destination> updateDestination(@PathVariable Long id, @RequestBody Destination destination) {
	        Optional<Destination> updatedDestination = destinationService.updateDestination(id, destination);
	        if (updatedDestination.isPresent()) {
	            return new ResponseEntity<>(updatedDestination.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Delete a Destination by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteDestination(@PathVariable Long id) {
	        boolean isDeleted = destinationService.deleteDestination(id);
	        if (isDeleted) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	}


