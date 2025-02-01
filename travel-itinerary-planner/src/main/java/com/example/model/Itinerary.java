package com.example.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;


	@Entity
	public class Itinerary {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Version  // Added version field for optimistic locking
	    private Long version;
	    
	    private String name;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	    @JsonBackReference
	    private User user;

	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itinerary")
	    @JsonManagedReference
	    private List<Destination> destinations;

	    private String startDate;
	    private String endDate;
	    private double budget;

	    // Default Constructor
	    public Itinerary() {
	    }

	    // Parameterized Constructor
	    public Itinerary(String name, User user, List<Destination> destinations, String startDate, String endDate, double budget) {
	        this.name = name;
	        this.user = user;
	        this.destinations = destinations;
	        this.startDate = startDate;
	        this.endDate = endDate;
	        this.budget = budget;
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

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }

	    public List<Destination> getDestinations() {
	        return destinations;
	    }

	    public void setDestinations(List<Destination> destinations) {
	        this.destinations = destinations;
	    }

	    public String getStartDate() {
	        return startDate;
	    }

	    public void setStartDate(String startDate) {
	        this.startDate = startDate;
	    }

	    public String getEndDate() {
	        return endDate;
	    }

	    public void setEndDate(String endDate) {
	        this.endDate = endDate;
	    }

	    public double getBudget() {
	        return budget;
	    }

	    public void setBudget(double budget) {
	        this.budget = budget;
	    }
	}
	