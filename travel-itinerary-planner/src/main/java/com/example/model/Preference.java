package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
	public class Preference {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String activityType; // Example: Adventure, Relaxation, Historical
	    private String budgetRange;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user;

	   
	    public Preference() {
	    }

	  
	    public Preference(String activityType, String budgetRange, User user) {
	        this.activityType = activityType;
	        this.budgetRange = budgetRange;
	        this.user = user;
	    }

	  
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getActivityType() {
	        return activityType;
	    }

	    public void setActivityType(String activityType) {
	        this.activityType = activityType;
	    }

	    public String getBudgetRange() {
	        return budgetRange;
	    }

	    public void setBudgetRange(String budgetRange) {
	        this.budgetRange = budgetRange;
	    }

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }
	}
	

