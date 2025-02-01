package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Destination;
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
}  
