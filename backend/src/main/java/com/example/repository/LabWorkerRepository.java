package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.LabWorker;

@Repository
public interface LabWorkerRepository extends JpaRepository<LabWorker, String> {
    // You can define additional repository methods if needed
}
