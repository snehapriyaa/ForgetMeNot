package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, String> {
}
