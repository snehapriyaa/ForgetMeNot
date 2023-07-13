package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.model.Test;
import com.example.repository.TestRepository;
import com.example.repository.TestLabTechPatientMapRepository;
import com.example.model.TestLabTechPatientMap;
import java.util.stream.Collectors;

@Service
public class TestLabTechPatientMapService {
    private final TestLabTechPatientMapRepository testLabTechPatientMapRepository;
    private final TestRepository testRepository;

    @Autowired
    public TestLabTechPatientMapService(TestLabTechPatientMapRepository testLabTechPatientMapRepository, TestRepository testRepository) {
        this.testLabTechPatientMapRepository = testLabTechPatientMapRepository;
        this.testRepository = testRepository;
    }

    public List<Test> getTestsByLabTechId(Long labTechId) {
        return testLabTechPatientMapRepository.findByLabTechnicianId(labTechId)
                .stream()
                .map(TestLabTechPatientMap::getTest)
                .collect(Collectors.toList());
    }

    public TestLabTechPatientMap saveTestLabTechPatientMap(TestLabTechPatientMap testLabTechPatientMap) {
        return testLabTechPatientMapRepository.save(testLabTechPatientMap);
    }

    // Other methods...
}
