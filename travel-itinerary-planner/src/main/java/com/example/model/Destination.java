package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

	@Entity
	public class Destination {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String country;
	    private String description;

	    // ManyToOne Relationship to Itinerary
	    @ManyToOne
	    @JoinColumn(name = "itinerary_id", nullable = false)
	    @JsonBackReference
	    private Itinerary itinerary;

	    
	    public Destination() {
	    }

	  
	    public Destination(String name, String country, String description, Itinerary itinerary) {
	        this.name = name;
	        this.country = country;
	        this.description = description;
	        this.itinerary = itinerary;
	    }

	   
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getCountry() {
	        return country;
	    }

	    public void setCountry(String country) {
	        this.country = country;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public Itinerary getItinerary() {
	        return itinerary;
	    }

	    public void setItinerary(Itinerary itinerary) {
	        this.itinerary = itinerary;
	    }
	}