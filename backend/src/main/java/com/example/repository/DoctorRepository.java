package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {
    // You can define additional repository methods if needed
}
