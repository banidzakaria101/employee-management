package com.employee.backend.controller;

import com.employee.backend.entities.Employee;
import com.employee.backend.repositoties.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @PostMapping("/update/{id}")
    public Employee updateEmployee(@RequestMapping Long id) {
        return employeeRepository.save(id);
    }

    @DeleteMapping
    public void deleteEmployee(@RequestBody Employee employee) {
        employeeRepository.delete(employee);
    }
}
