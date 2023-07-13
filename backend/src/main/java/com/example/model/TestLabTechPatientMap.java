package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "test_labtech_patient_map")
public class TestLabTechPatientMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @MapsId("labTechId")
    @JoinColumn(name = "labTechId")
    private LabTechnician labTechnician;

    @ManyToOne
    @MapsId("patientId")
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @MapsId("testId")
    @JoinColumn(name = "test_id")
    private Test test;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LabTechnician getLabTechnician() {
        return labTechnician;
    }

    public void setLabTechnician(LabTechnician labTechnician) {
        this.labTechnician = labTechnician;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
