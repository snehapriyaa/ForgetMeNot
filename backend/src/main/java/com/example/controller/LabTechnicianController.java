package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LabTechnician;
import com.example.service.LabTechnicianService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
// @RequestMapping("/LabTechnicians")
public class LabTechnicianController {

    private final LabTechnicianService LabTechnicianService;

    @Autowired
    public LabTechnicianController(LabTechnicianService LabTechnicianService) {
        this.LabTechnicianService = LabTechnicianService;
    }

    @GetMapping("/LabTechnicians")
    public ResponseEntity<List<LabTechnician>> getAllLabTechnicians() {
        List<LabTechnician> LabTechnicians = LabTechnicianService.getAllLabTechnicians();
        return new ResponseEntity<>(LabTechnicians, HttpStatus.OK);
    }

    @GetMapping("/{LabTechnicianId}")
    public ResponseEntity<LabTechnician> getLabTechnicianById(@PathVariable String LabTechnicianId) {
        LabTechnician LabTechnician = LabTechnicianService.getLabTechnicianById(LabTechnicianId);
        if (LabTechnician != null) {
            return new ResponseEntity<>(LabTechnician, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addLabTechnician")
    @ResponseBody
    public ResponseEntity<LabTechnician> addLabTechnician(@RequestBody LabTechnician LabTechnician) {
        LabTechnician createdLabTechnician = LabTechnicianService.addLabTechnician(LabTechnician);
        return new ResponseEntity<>(createdLabTechnician, HttpStatus.CREATED);
    }
}
