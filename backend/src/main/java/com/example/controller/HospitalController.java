package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;
import java.util.Date;
//import com.example.exceptions.NotFoundException;

import com.example.service.HospitalService;
import com.example.model.Hospital;
import com.example.model.HospitalRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping("/api/hospitals")
public class HospitalController {
    private final HospitalService hospitalService;

    @Autowired
    public HospitalController(HospitalService hospitalService) {
        System.out.print("Here is Good!");

        this.hospitalService = hospitalService;
        hospitalService.clearHibernateCache();
    }

    // @GetMapping
    // public List<Hospital> getAllHospitals() {
    //     return hospitalService.getAllHospitals();
    // }

    // @GetMapping("/{hospitalId}")
    // public Optional<Hospital> getHospitalById(@PathVariable String hospitalId) {
    //     return hospitalService.getHospitalById(hospitalId);
    // }
    
    @GetMapping("/test")
    @ResponseBody
    public String tester() {
        System.out.print("HERE good at test!");
        return "Up";
    }

    @PostMapping("/addHospital")
    @ResponseBody
    public Hospital addHospital(@RequestBody HospitalRequest hospitalRequest) {

        System.out.print(hospitalRequest);

        Hospital hospital = new Hospital();
        hospital.setHospitalId("hosp01");
        hospital.setHospitalName(hospitalRequest.getName());
        //hospital.setUsername(hospitalRequest.getEmail());
        //hospital.setPassword(hospitalRequest.getUserType());
        hospital.setEmailId(hospitalRequest.getEmail());
        hospital.setAddress1(hospitalRequest.getAddressLine1());
        hospital.setAddress2(hospitalRequest.getAddressLine2());
        hospital.setState(hospitalRequest.getState());
        hospital.setCity(hospitalRequest.getCity());
        hospital.setCountry(hospitalRequest.getCountry());
        hospital.setZipCode(hospitalRequest.getZipCode());
        hospital.setCreatedAt(new Date(System.currentTimeMillis()));

        System.out.println("hospital req done!");
        return hospitalService.addHospital(hospital);

    }

    // @PutMapping("/{hospitalId}")
    // public Hospital updateHospital(@PathVariable String hospitalId, @RequestBody Hospital updatedHospital) {
    //     Optional<Hospital> hospital = hospitalService.getHospitalById(hospitalId);
    //     if (hospital.isPresent()) {
    //         Hospital existingHospital = hospital.get();
    //         existingHospital.setHospitalName(updatedHospital.getHospitalName());
    //         existingHospital.setUsername(updatedHospital.getUsername());
    //         existingHospital.setPassword(updatedHospital.getPassword());
    //         existingHospital.setEmailId(updatedHospital.getEmailId());
    //         existingHospital.setAddress1(updatedHospital.getAddress1());
    //         existingHospital.setAddress2(updatedHospital.getAddress2());
    //         existingHospital.setState(updatedHospital.getState());
    //         existingHospital.setCity(updatedHospital.getCity());
    //         existingHospital.setCountry(updatedHospital.getCountry());
    //         existingHospital.setZipCode(updatedHospital.getZipCode());
    //         existingHospital.setCreatedAt(updatedHospital.getCreatedAt());

    //         return hospitalService.updateHospital(existingHospital);
    //     } //else {
    //         //throw new NotFoundException("Hospital not found with ID: " + hospitalId);
    //    // }
    //    return null;
    // }

    // @DeleteMapping("/{hospitalId}")
    // public void deleteHospital(@PathVariable String hospitalId) {
    //     hospitalService.deleteHospital(hospitalId);
    // }
}

