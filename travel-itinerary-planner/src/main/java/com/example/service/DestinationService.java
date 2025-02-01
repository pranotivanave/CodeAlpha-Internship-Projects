package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Destination;
import com.example.model.Itinerary;
import com.example.repository.DestinationRepository;
import com.example.repository.ItineraryRepository;

import jakarta.transaction.Transactional;

@Service
	public class DestinationService {

	    private final DestinationRepository destinationRepository;
	    private final ItineraryRepository itineraryRepository;

	    @Autowired
	    public DestinationService(DestinationRepository destinationRepository, ItineraryRepository itineraryRepository) {
	        this.destinationRepository = destinationRepository;
	        this.itineraryRepository = itineraryRepository;
	    }

	   
	    @Transactional
	    public Destination createDestination(Destination destination) {
	      
	        Itinerary itinerary = itineraryRepository.findById(destination.getItinerary().getId())
	                .orElseThrow(() -> new RuntimeException("Itinerary not found"));

	       
	        destination.setItinerary(itinerary);

	      
	        return destinationRepository.save(destination);
	    }
	    
	    public List<Destination> getAllDestinations() {
	        return destinationRepository.findAll();
	    }

	  
	    public Optional<Destination> getDestinationById(Long id) {
	        return destinationRepository.findById(id);
	    }

	   
	    public Optional<Destination> updateDestination(Long id, Destination destination) {
	        if (destinationRepository.existsById(id)) {
	            destination.setId(id);
	            return Optional.of(destinationRepository.save(destination));
	        }
	        return Optional.empty();
	    }

	    
	    public boolean deleteDestination(Long id) {
	        if (destinationRepository.existsById(id)) {
	            destinationRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	}


