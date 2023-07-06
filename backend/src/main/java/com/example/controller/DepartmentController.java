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

import com.example.model.Department;
import com.example.model.DepartmentRequest;
import com.example.service.DepartmentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
// @RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable String departmentId) {
        Department department = departmentService.getDepartmentById(departmentId);
        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addDepartment")
    @ResponseBody
    public ResponseEntity<Department> addDepartment(@RequestBody DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setDepartmentId("dep-01");
        department.setHospitalId(departmentRequest.getHospitalId());
        department.setAddress1(departmentRequest.getAddressLine1());
        department.setAddress2(departmentRequest.getAddressLine2());
        department.setCity(departmentRequest.getCity());
        department.setCountry(departmentRequest.getCountry());
        department.setZipCode(departmentRequest.getZipCode());
        department.setCreatedAt(new java.sql.Date(System.currentTimeMillis()));
        Department createdDepartment = departmentService.addDepartment(department);
        return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
    }
}
