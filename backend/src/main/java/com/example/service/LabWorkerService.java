package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.LabWorker;
import com.example.repository.LabWorkerRepository;

@Service
public class LabWorkerService {

    private final LabWorkerRepository labWorkerRepository;

    @Autowired
    public LabWorkerService(LabWorkerRepository labWorkerRepository) {
        this.labWorkerRepository = labWorkerRepository;
    }

    public List<LabWorker> getAllLabWorkers() {
        return labWorkerRepository.findAll();
    }

    public LabWorker getLabWorkerById(String labWorkerId) {
        Optional<LabWorker> optionalLabWorker = labWorkerRepository.findById(labWorkerId);
        return optionalLabWorker.orElse(null);
    }

    public LabWorker addLabWorker(LabWorker labWorker) {
        return labWorkerRepository.save(labWorker);
    }
}
