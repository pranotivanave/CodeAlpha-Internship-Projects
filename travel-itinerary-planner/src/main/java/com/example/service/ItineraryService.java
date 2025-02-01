package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Destination;
import com.example.model.Itinerary;
import com.example.repository.ItineraryRepository;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
	public class ItineraryService {

	

	    private static final Logger logger = LoggerFactory.getLogger(ItineraryService.class);

	    @Autowired
	    private ItineraryRepository itineraryRepository;

	    private static final double BASE_COST_PER_DESTINATION = 100;  
	    private static final double COST_PER_DAY = 50; 

	
	    @Transactional
	    public String createItinerary(Itinerary itinerary) {
	        logger.info("Creating itinerary with data: {}", itinerary);
	        try {
	            calculateAndSetBudget(itinerary);
	            itineraryRepository.save(itinerary);
	            String successMessage = "Your itinerary has been created successfully";
	            logger.info(successMessage);
	            return successMessage;
	        } catch (Exception e) {
	            logger.error("Error while creating itinerary: {}", e.getMessage());
	            throw e;
	        }
	    }

	    public List<Itinerary> getItinerariesByUser(Long userId) {
	        logger.info("Fetching itineraries for user with ID: {}", userId);
	        return itineraryRepository.findByUserId(userId);
	    }

	    public Itinerary getItineraryById(Long id) {
	        logger.info("Fetching itinerary by ID: {}", id);
	        Optional<Itinerary> itinerary = itineraryRepository.findById(id);
	        if (itinerary.isEmpty()) {
	            logger.warn("No itinerary found with ID: {}", id);
	        }
	        return itinerary.orElse(null);
	    }

	    public Itinerary updateItinerary(Itinerary itinerary) {
	        logger.info("Updating itinerary with ID: {}", itinerary.getId());
	        if (itineraryRepository.existsById(itinerary.getId())) {
	            try {
	                calculateAndSetBudget(itinerary);
	                return itineraryRepository.save(itinerary);
	            } catch (Exception e) {
	                logger.error("Error while updating itinerary with ID {}: {}", itinerary.getId(), e.getMessage());
	                throw e;
	            }
	        } else {
	            String errorMsg = "Itinerary not found with ID: " + itinerary.getId();
	            logger.error(errorMsg);
	            throw new RuntimeException(errorMsg);
	        }
	    }

	    public void deleteItinerary(Long itineraryId) {
	        logger.info("Deleting itinerary with ID: {}", itineraryId);
	        if (itineraryRepository.existsById(itineraryId)) {
	            itineraryRepository.deleteById(itineraryId);
	        } else {
	            String errorMsg = "Itinerary not found with ID: " + itineraryId;
	            logger.error(errorMsg);
	            throw new RuntimeException(errorMsg);
	        }
	    }

	   
	    private void calculateAndSetBudget(Itinerary itinerary) {
	        logger.info("Calculating budget for itinerary: {}", itinerary);
	        if (itinerary.getDestinations() == null || itinerary.getStartDate() == null || itinerary.getEndDate() == null) {
	            String errorMsg = "Required data is missing. Please provide destinations and valid start and end dates.";
	            logger.error(errorMsg);
	            throw new IllegalArgumentException(errorMsg);
	        }

	        double totalBudget = 0;
	        try {
	            totalBudget += calculateDestinationCost(itinerary);
	            totalBudget += calculateDurationCost(itinerary.getStartDate(), itinerary.getEndDate());
	            itinerary.setBudget(totalBudget);
	            logger.info("Calculated budget: {}", totalBudget);
	        } catch (Exception e) {
	            logger.error("Error while calculating budget: {}", e.getMessage());
	            throw e;
	        }
	    }

	   
	    private double calculateDestinationCost(Itinerary itinerary) {
	        double destinationCost = 0;

	        for (Destination destination : itinerary.getDestinations()) {
	            destinationCost += BASE_COST_PER_DESTINATION;  
	        }
	        logger.info("Calculated destination cost: {}", destinationCost);
	        return destinationCost;
	    }

	   
	    private double calculateDurationCost(String startDate, String endDate) {
	        long duration = calculateDuration(startDate, endDate);
	        double durationCost = duration * COST_PER_DAY;
	        logger.info("Calculated duration cost for {} days: {}", duration, durationCost);
	        return durationCost;
	    }

	   
	    private long calculateDuration(String startDate, String endDate) {
	        try {
	            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date start = sdf.parse(startDate);
	            java.util.Date end = sdf.parse(endDate);

	            long diffInMillis = end.getTime() - start.getTime();
	            long duration = diffInMillis / (1000 * 60 * 60 * 24);  
	            if (duration < 0) {
	                String errorMsg = "End date cannot be before start date.";
	                logger.error(errorMsg);
	                throw new IllegalArgumentException(errorMsg);
	            }
	            return duration;
	        } catch (java.text.ParseException e) {
	            logger.error("Error parsing dates: Start Date: {}, End Date: {}", startDate, endDate, e);
	            throw new RuntimeException("Invalid date format. Please use 'yyyy-MM-dd'.", e);
	        }
	    }
	}
