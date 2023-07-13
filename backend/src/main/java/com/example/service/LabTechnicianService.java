package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.LabTechnician;
import com.example.repository.LabTechnicianRepository;

@Service
public class LabTechnicianService {

    private final LabTechnicianRepository LabTechnicianRepository;

    @Autowired
    public LabTechnicianService(LabTechnicianRepository LabTechnicianRepository) {
        this.LabTechnicianRepository = LabTechnicianRepository;
    }

    public List<LabTechnician> getAllLabTechnicians() {
        return LabTechnicianRepository.findAll();
    }

    public LabTechnician getLabTechnicianById(String LabTechnicianId) {
        Optional<LabTechnician> optionalLabTechnician = LabTechnicianRepository.findById(LabTechnicianId);
        return optionalLabTechnician.orElse(null);
    }

    public LabTechnician addLabTechnician(LabTechnician LabTechnician) {
        return LabTechnicianRepository.save(LabTechnician);
    }
}
