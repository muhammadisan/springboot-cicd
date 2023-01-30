package com.example.restfuljpa.service;

import com.example.restfuljpa.entity.JobVacancy;
import com.example.restfuljpa.repository.JobVacancyRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class JobVacancyService {
    @Autowired
    JobVacancyRepository jobVacancyRepository;

    public List<JobVacancy> getJobVacancies() {
        return (List<JobVacancy>) jobVacancyRepository.findAll();
    }

    public JobVacancy getJobVacancyById(@PathVariable Integer jobVacancyId) {
        return jobVacancyRepository.findById(jobVacancyId).get();
    }

    public JobVacancy addJobVacancy(@NotNull JobVacancy addedJobVacancy) {
        return jobVacancyRepository.save(addedJobVacancy);
    }

    public List<JobVacancy> searchJobVacancy(@PathVariable String searchedJobVacancy) {
        return jobVacancyRepository.searchByPositionLike(String.valueOf(searchedJobVacancy));
    }

    public JobVacancy updateJobVacancy(JobVacancy updatedJobVacancy) {
        return jobVacancyRepository.save(updatedJobVacancy);
    }

    public String deleteJobVacancy(Integer jobVacancyId) {
        jobVacancyRepository.deleteById(jobVacancyId);
        return "job vacancy with id " + jobVacancyId + " is deleted";
    }
}
