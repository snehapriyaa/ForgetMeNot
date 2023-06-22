package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.model.Hospital;
import com.example.repository.HospitalRepository;

import org.hibernate.SessionFactory;
//import org.hibernate.Session;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;


    @Autowired
    private SessionFactory sessionFactory;

    public void clearHibernateCache(){
        sessionFactory.getCache().evictAllRegions();
    }

    @Autowired
    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Optional<Hospital> getHospitalById(String hospitalId) {
        return hospitalRepository.findById(hospitalId);
    }

    public Hospital addHospital(Hospital hospital) {
        System.out.println(hospital.getAddress1());
        System.out.println();
        return hospitalRepository.save(hospital);
    }

    public Hospital updateHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }
}
