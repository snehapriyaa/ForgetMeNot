package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.TestLabTechPatientMap;

import java.util.List;


@Repository
public interface TestLabTechPatientMapRepository extends JpaRepository<TestLabTechPatientMap, Long> {
    List<TestLabTechPatientMap> findByLabTechnicianId(Long labTechnicianId);
}
