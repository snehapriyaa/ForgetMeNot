package com.example.model;

import javax.persistence.*;
import java.util.Date;
//import java.sql.Timestamp;

@Entity
@Table(name = "hospitals")
public class Hospital {
    @Id
    @Column(name = "Hospital_id", length = 10)
    private String hospitalId;

    @Column(name = "Name", length = 45)
    private String hospitalName;

    @Column(name = "Email", length = 45)
    private String emailId;

    @Column(name = "Addr1")
    private String address1;

    @Column(name = "Addr2")
    private String address2;

    @Column(name = "State", length = 45)
    private String state;

    @Column(name = "City", length = 45)
    private String city;

    @Column(name = "Country", length = 45)
    private String country;

    @Column(name = "Zipcode")
    private String zipCode;

    @Column(name = "created_at")
    private Date createdAt;

    // Getters and setters
    // ...
    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
