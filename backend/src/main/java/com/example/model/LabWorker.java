package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LabWorkers")
public class LabWorker {

    @Id
    @Column(name = "LabWorkerId", length = 10)
    private String labWorkerId;

    @Column(name = "DepartmentId", length = 10)
    private String departmentId;

    @Column(name = "FirstName", length = 45)
    private String firstName;

    @Column(name = "LastName", length = 45)
    private String lastName;

    @Column(name = "DOB", length = 10)
    private Date dob;

    @Column(name = "Age", length = 3)
    private Integer age;

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

    // Generate getters and setters

    // You can add constructors, equals/hashCode, toString methods if needed
    public String getLabWorkerId() {
        return labWorkerId;
    }

    public void setLabWorkerId(String labWorkerId) {
        this.labWorkerId = labWorkerId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
