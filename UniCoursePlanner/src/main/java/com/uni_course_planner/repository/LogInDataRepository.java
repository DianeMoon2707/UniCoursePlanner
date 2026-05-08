package com.uni_course_planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uni_course_planner.relation.LogInData;

@Repository
public interface LogInDataRepository extends JpaRepository<LogInData, Long>{

}
