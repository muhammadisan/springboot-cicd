package com.example.restfuljpa.repository;

import com.example.restfuljpa.entity.JobVacancy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobVacancyRepository extends CrudRepository<JobVacancy, Integer> {
    @Query("SELECT a FROM JobVacancy a WHERE LOWER(a.position) LIKE LOWER(CONCAT('%', :search,'%'))")
    List<JobVacancy> searchByPositionLike(@Param("search") String search);
}
