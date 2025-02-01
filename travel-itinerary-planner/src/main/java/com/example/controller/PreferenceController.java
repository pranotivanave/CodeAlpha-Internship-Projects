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

import com.example.model.Preference;
import com.example.service.PreferenceService;

@RestController
	@RequestMapping("/api/preferences")
	public class PreferenceController {

	    private final PreferenceService preferenceService;

	    @Autowired
	    public PreferenceController(PreferenceService preferenceService) {
	        this.preferenceService = preferenceService;
	    }

	    // Create a new Preference
	    @PostMapping
	    public ResponseEntity<Preference> createPreference(@RequestBody Preference preference) {
	        Preference createdPreference = preferenceService.createPreference(preference);
	        return new ResponseEntity<>(createdPreference, HttpStatus.CREATED);
	    }

	    // Get all Preferences
	    @GetMapping
	    public ResponseEntity<List<Preference>> getAllPreferences() {
	        List<Preference> preferences = preferenceService.getAllPreferences();
	        return new ResponseEntity<>(preferences, HttpStatus.OK);
	    }

	    // Get a single Preference by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Preference> getPreferenceById(@PathVariable Long id) {
	        Optional<Preference> preference = preferenceService.getPreferenceById(id);
	        if (preference.isPresent()) {
	            return new ResponseEntity<>(preference.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Update an existing Preference
	    @PutMapping("/{id}")
	    public ResponseEntity<Preference> updatePreference(@PathVariable Long id, @RequestBody Preference preference) {
	        Optional<Preference> updatedPreference = preferenceService.updatePreference(id, preference);
	        if (updatedPreference.isPresent()) {
	            return new ResponseEntity<>(updatedPreference.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    // Delete a Preference by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deletePreference(@PathVariable Long id) {
	        boolean isDeleted = preferenceService.deletePreference(id);
	        if (isDeleted) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	}
