package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Preference;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
}