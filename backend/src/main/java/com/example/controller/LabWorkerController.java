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

import com.example.model.LabWorker;
import com.example.service.LabWorkerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
// @RequestMapping("/labworkers")
public class LabWorkerController {

    private final LabWorkerService labWorkerService;

    @Autowired
    public LabWorkerController(LabWorkerService labWorkerService) {
        this.labWorkerService = labWorkerService;
    }

    @GetMapping("/labworkers")
    public ResponseEntity<List<LabWorker>> getAllLabWorkers() {
        List<LabWorker> labWorkers = labWorkerService.getAllLabWorkers();
        return new ResponseEntity<>(labWorkers, HttpStatus.OK);
    }

    @GetMapping("/{labWorkerId}")
    public ResponseEntity<LabWorker> getLabWorkerById(@PathVariable String labWorkerId) {
        LabWorker labWorker = labWorkerService.getLabWorkerById(labWorkerId);
        if (labWorker != null) {
            return new ResponseEntity<>(labWorker, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addLabWorker")
    @ResponseBody
    public ResponseEntity<LabWorker> addLabWorker(@RequestBody LabWorker labWorker) {
        LabWorker createdLabWorker = labWorkerService.addLabWorker(labWorker);
        return new ResponseEntity<>(createdLabWorker, HttpStatus.CREATED);
    }
}
