package com.example.restfuljpa.controller;

import com.example.restfuljpa.entity.Employee;
import com.example.restfuljpa.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        log.info("[DEV] get /employees is called");
        try {
            ResponseEntity<List<Employee>> response = new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
            log.info("[DEV] response length is: "+response.getBody().size());

            return response;
        } catch (InvalidDataAccessResourceUsageException e) { //db exists, tbl doesn't exist
            log.info("[DEV] exception message is: "+e);
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (CannotCreateTransactionException e) { //during springboot run, db is deleted
            log.info("[DEV] exception message is: "+e);
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } catch (Exception e) { //any other exceptions
            log.info("[DEV] exception message is: "+e);
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee addedEmployee) {
        log.info("[DEV] post /employee is called");
        if ((int) addedEmployee.getId() < 1) return new ResponseEntity<>(HttpStatus.CONFLICT);
        try {
            return new ResponseEntity<>(employeeService.addEmployee(addedEmployee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/employee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee updatedEmployee) {
        log.info("[DEV] put /employee is called");
        if ((int) updatedEmployee.getId() < 1) return new ResponseEntity<>(HttpStatus.CONFLICT);
        try {
            return new ResponseEntity<>(employeeService.updateEmployee(updatedEmployee), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("employee/{employeeId}")
    public ResponseEntity<String> deleteCountry(@PathVariable Integer employeeId) {
        log.info("[DEV] delete employee/{employeeId} is called");
        try {
            return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
