package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Itinerary;

public interface ItineraryRepository   extends JpaRepository<Itinerary, Long> {
    List<Itinerary> findByUserId(Long userId);
}


