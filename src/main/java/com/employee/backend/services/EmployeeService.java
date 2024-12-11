package com.employee.backend.services;

import com.employee.backend.entities.Employee;
import com.employee.backend.repositoties.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {

        return employeeRepository.findById(id).get();
    }


    public Employee updateEmployee(int id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee != null) {
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());

            return employeeRepository.save(existingEmployee);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
