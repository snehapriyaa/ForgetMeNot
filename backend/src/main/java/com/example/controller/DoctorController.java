package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.example.model.Doctor;
import com.example.model.DoctorRequest;
import com.example.service.DoctorService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
// @RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable String doctorId) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addDoctor")
    @ResponseBody
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorRequest doctorRequest) {
        Doctor doctor = new Doctor();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        doctor.setFirstName(doctorRequest.getFirstName());
        doctor.setLastName(doctorRequest.getLastName());
        Date date = new Date();
        try {
            date = formatter.parse(doctorRequest.getDateOfBirth());
        } catch (ParseException e) {
            System.out.println("Please enter a valid date! Format is yyyy/mm/dd");
        }
        doctor.setDateOfBirth(new java.sql.Date(date.getTime()));
        doctor.setEmailId(doctorRequest.getEmail());
        doctor.setAddress1(doctorRequest.getAddressLine1());
        doctor.setAddress2(doctorRequest.getAddressLine2());
        doctor.setCity(doctorRequest.getCity());
        doctor.setDepartmentId(doctorRequest.getDepartment());
        doctor.setAge(Integer.parseInt(DoctorRequest.getAge()));
        doctor.setCountry(doctorRequest.getCountry());
        doctor.setZipCode(doctorRequest.getZipCode());
        doctor.setCreatedAt(new java.sql.Date(System.currentTimeMillis()));

        Doctor createdDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }
}
