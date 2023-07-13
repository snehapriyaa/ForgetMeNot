package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.LabTechnician;

@Repository
public interface LabTechnicianRepository extends JpaRepository<LabTechnician, String> {
    // You can define additional repository methods if needed
}
