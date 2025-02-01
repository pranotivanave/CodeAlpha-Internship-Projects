package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Preference;
import com.example.repository.PreferenceRepository;

@Service
	public class PreferenceService {

	    private final PreferenceRepository preferenceRepository;

	    @Autowired
	    public PreferenceService(PreferenceRepository preferenceRepository) {
	        this.preferenceRepository = preferenceRepository;
	    }

	   
	    public Preference createPreference(Preference preference) {
	        return preferenceRepository.save(preference);
	    }

	      public List<Preference> getAllPreferences() {
	        return preferenceRepository.findAll();
	    }

	    
	    public Optional<Preference> getPreferenceById(Long id) {
	        return preferenceRepository.findById(id);
	    }

	 
	    public Optional<Preference> updatePreference(Long id, Preference preference) {
	        if (preferenceRepository.existsById(id)) {
	            preference.setId(id);  
	            return Optional.of(preferenceRepository.save(preference));
	        } else {
	            return Optional.empty();
	        }
	    }

	   	    public boolean deletePreference(Long id) {
	        if (preferenceRepository.existsById(id)) {
	            preferenceRepository.deleteById(id);
	            return true;
	        } else {
	            return false;
	        }
	    }
	}

