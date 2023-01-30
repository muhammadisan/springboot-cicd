package com.example.restfuljpa.controller;

import com.example.restfuljpa.entity.JobVacancy;
import com.example.restfuljpa.service.JobVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobVacancyController {
    @Autowired
    JobVacancyService jobVacancyService;

    @GetMapping("/job-vacancies")
    public ResponseEntity<List<JobVacancy>> getJobVacancies() {
        try {
            ResponseEntity<List<JobVacancy>> response = new ResponseEntity<>(jobVacancyService.getJobVacancies(), HttpStatus.OK);
            return response;
        } catch (InvalidDataAccessResourceUsageException e) { //db exists, tbl doesn't exist
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (CannotCreateTransactionException e) { //during springboot run, db is deleted
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        } catch (Exception e) { //any other exceptions
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }


    @GetMapping("/job-vacancy/{jobVacancyId}")
    public ResponseEntity<JobVacancy> getJobVacancyById(@PathVariable Integer jobVacancyId) {
        try {
            return new ResponseEntity<>(jobVacancyService.getJobVacancyById(jobVacancyId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/job-vacancy")
    public ResponseEntity<JobVacancy> addJobVacancy(@RequestBody JobVacancy addedJobVacancy) {
        try {
            return new ResponseEntity<>(jobVacancyService.addJobVacancy(addedJobVacancy), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/job-vacancy/{search}")
    public ResponseEntity<List<JobVacancy>> searchJobVacancy(@PathVariable String search) {
        try {
            ResponseEntity<List<JobVacancy>> response = new ResponseEntity<>(jobVacancyService.searchJobVacancy(search), HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/job-vacancy")
    public ResponseEntity<JobVacancy> updateJobVacancy(@RequestBody JobVacancy updatedJobVacancy) {
        if ((int) updatedJobVacancy.getId() < 1) return new ResponseEntity<>(HttpStatus.CONFLICT);
        try {
            return new ResponseEntity<>(jobVacancyService.updateJobVacancy(updatedJobVacancy), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("job-vacancy/{jobVacancyId}")
    public ResponseEntity<String> deleteJobVacancy(@PathVariable Integer jobVacancyId) {
        try {
            return new ResponseEntity<>(jobVacancyService.deleteJobVacancy(jobVacancyId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
