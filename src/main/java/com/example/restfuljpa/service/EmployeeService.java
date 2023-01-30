package com.example.restfuljpa.service;

import com.example.restfuljpa.entity.Employee;
import com.example.restfuljpa.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee addEmployee(@NotNull Employee addedEmployee) {
        log.info("[DEV] new employee is " + addedEmployee.getFirstName() + " " + addedEmployee.getLastName());
        Integer id = (int) employeeRepository.count() + 1;
        Employee employee = new Employee(id, addedEmployee.getFirstName(), addedEmployee.getLastName());
        employeeRepository.save(employee);
        return employeeRepository.findById(id).get();
    }

    public Employee updateEmployee(Employee updatedEmployee) {
        employeeRepository.save(updatedEmployee);
        return employeeRepository.findById(updatedEmployee.getId()).get();
    }

    public String deleteEmployee(Integer employeeId) {
        log.info("[DEV]");
        employeeRepository.deleteById(employeeId);
        return "employee with id " + employeeId + " deleted";
    }
}
