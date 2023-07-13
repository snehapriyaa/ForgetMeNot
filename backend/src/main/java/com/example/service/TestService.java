package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.example.model.Test;
import com.example.repository.TestRepository;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    // Other dependencies and methods

    public Test saveTest(Test test) {
        return testRepository.save(test);
    }

    public Test completeTest(Long id) {
        Optional<Test> optionalTest = testRepository.findById(id);
        if (optionalTest.isPresent()) {
            Test test = optionalTest.get();
            test.setCompleted(true);
            return testRepository.save(test);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Test not found");
        }
    }

    public Test findById(Long id) {
        Optional<Test> optionalTest = testRepository.findById(id);
        return optionalTest.orElse(null);
    }

    // Other methods
}
