package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Doctors")
public class Doctor {
    @Id
    @Column(name = "DoctorId", length = 10)
    private String doctorId;

    @Column(name = "DepartmentId", length = 10)
    private String departmentId;

    @Column(name = "FirstName", length = 45)
    private String firstName;

    @Column(name = "LastName", length = 45)
    private String lastName;

    @Column(name = "SecretQues1", length = 245)
    private String secretQuestion1;

    @Column(name = "SecretAns1", length = 245)
    private String secretAnswer1;

    @Column(name = "SecretQues2", length = 245)
    private String secretQuestion2;

    @Column(name = "SecretAns2", length = 245)
    private String secretAnswer2;

    @Column(name = "SecretQues3", length = 245)
    private String secretQuestion3;

    @Column(name = "SecretAns3", length = 245)
    private String secretAnswer3;

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
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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

    public String getSecretQues1() {
        return secretQuestion1;
    }

    public void setSecretQues1(String secretQuestion1) {
        this.secretQuestion1 = secretQuestion1;
    }

    public String getSecretAns1() {
        return secretAnswer1;
    }

    public void setSecretAns1(String secretAnswer1) {
        this.secretAnswer1 = secretAnswer1;
    }

    public String getSecretQues2() {
        return secretQuestion2;
    }

    public void setSecretQues2(String secretQuestion2) {
        this.secretQuestion2 = secretQuestion2;
    }

    public String getSecretAns2() {
        return secretAnswer2;
    }

    public void setSecretAns2(String secretAnswer2) {
        this.secretAnswer2 = secretAnswer2;
    }

    public String getSecretQues3() {
        return secretQuestion3;
    }

    public void setSecretQues3(String secretQuestion3) {
        this.secretQuestion3 = secretQuestion3;
    }

    public String getSecretAns3() {
        return secretAnswer3;
    }

    public void setSecretAns3(String secretAnswer3) {
        this.secretAnswer3 = secretAnswer3;
    }

    public Date getDateOfBirth() {
        return dob;
    }

    public void setDateOfBirth(Date dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
